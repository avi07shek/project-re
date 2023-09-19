package com.system.hotel_reservation_system.services.impl;

import com.system.hotel_reservation_system.config.PasswordEncoderUtil;

import com.system.hotel_reservation_system.entity.Review;
import com.system.hotel_reservation_system.entity.User;

import com.system.hotel_reservation_system.exception.AppException;

import com.system.hotel_reservation_system.pojo.ReviewPojo;
import com.system.hotel_reservation_system.pojo.UserPojo;

import com.system.hotel_reservation_system.repo.ReviewRepo;
import com.system.hotel_reservation_system.repo.UserRepo;
import com.system.hotel_reservation_system.services.UserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final JavaMailSender mailSender;
   private final ThreadPoolTaskExecutor taskExecutor;
//    private final NewsRepo newsRepo;
    private final ReviewRepo reviewRepo;
    @Autowired
    @Qualifier("emailConfigBean")
    private Configuration emailConfig;
    @Override
    public String saveUser(UserPojo userPojo) {
        User user = new User();


        user.setEmail(userPojo.getEmail());
        user.setFullname(userPojo.getFullname());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userPojo.getPassword()));
        userRepo.save(user);
        return "Created";

    }

    @Override
    public UserPojo findByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));
        return new UserPojo(user);
    }

//    @Override
//    public UserPojo findByEmail(String email) {
//        User user = (User) userRepo.findByEmail(email)
//                .orElseThrow(()-> new RuntimeException("Invalid User email"));
//        return new UserPojo(user);
//    }
//
//    @Override
//    public UserPojo findByPassword(String password) {
//        User user = (User) userRepo.findByPassword(password)
//                .orElseThrow(() -> new RuntimeException("Invalid User password"));
//        return new UserPojo(user);
//    }

    @Override
    public String submitReview(ReviewPojo reviewPojo) {
        Review review=new Review();
        review.setId(reviewPojo.getId());
        review.setLocation(reviewPojo.getLocation());
        review.setPricing(reviewPojo.getPricing());
        review.setComfort(reviewPojo.getComfort());
        review.setService(reviewPojo.getService());
        review.setName(reviewPojo.getName());
        review.setEmail(reviewPojo.getEmail());
        review.setDescription(reviewPojo.getDescription());
        reviewRepo.save(review);
        return "send";
    }

    @Override
    public String updateResetPassword(String email) {
        User user = (User) userRepo.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Invalid User email"));
        String updated_password = generatePassword();
        try {
            userRepo.updatePassword(updated_password,email);
            return "CHANGED";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ds";
    }

    @Override
    public List<Review> fetchAll() {
        return this.reviewRepo.findAll();
    }

    @Override
    public Review fetchbyid(Integer id) {
        Review review=reviewRepo.findById(id).orElseThrow(()->
                new RuntimeException("notfound"));
        review=Review.builder().name(review.getName())
                .description(review.getDescription())
                .build();
        return review;
    }

    @Override
    public void deletebyid(Integer id) {
        reviewRepo.deleteById(id);
    }


    public String generatePassword() {
        int length = 8;
        String password = "";
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            int randomChar = (int)(r.nextInt(94) + 33);
            char c = (char)randomChar;
            password += c;
        }
        return password;
    }

    private void sendPassword(String email, String password ){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your new password is: "+ password);
        message.setText(password);
        mailSender.send(message);
    }
    @Override
    public void processPasswordResetRequest(String email){
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            String password = generatePassword();
            sendPassword(email, password);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodePassword = passwordEncoder.encode(password);
            user.setPassword(encodePassword);
            userRepo.save(user);
        }
    }
    @Override
    public void sendEmail() {
        try {
            Map<String, String> model = new HashMap<>();

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Template template = emailConfig.getTemplate("emailTemp.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            mimeMessageHelper.setTo("sendfrom@yopmail.com");
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject("Registration");
            mimeMessageHelper.setFrom("sendTo@yopmail.com");

            taskExecutor.execute(new Thread() {
                public void run() {
                    mailSender.send(message);
                }
            });
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
