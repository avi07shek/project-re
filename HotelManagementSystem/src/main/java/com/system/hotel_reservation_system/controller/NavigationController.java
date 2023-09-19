package com.system.hotel_reservation_system.controller;
import com.system.hotel_reservation_system.entity.Book;
import com.system.hotel_reservation_system.entity.Room;
import com.system.hotel_reservation_system.pojo.BookPojo;
import com.system.hotel_reservation_system.pojo.RoomPojo;
import com.system.hotel_reservation_system.services.BookingService;
import com.system.hotel_reservation_system.services.RoomService;
import com.system.hotel_reservation_system.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class NavigationController {
    private  final RoomService roomService;
    private  final BookingService bookingService;

    private final UserService userService;
    @GetMapping("/about")
    public String GetAbout(){
        return "/about";
    }

    @GetMapping("/gallery")
    public String GetGallery(){
        return "/gallery";
    }

    @GetMapping("/news")
    public String GetNews(Model model){
        return "/news";
    }

    @GetMapping("/contact")
    public String GetContact(){
        return "/contacts";
    }

    @GetMapping("/payment")
    public String GetPayment(){
        return "/payment";
    }



    @GetMapping("/books/{id}")
    public String GetBook(@PathVariable Integer id, Model model, Principal principal){
        Room room=roomService.fetchById(id);
        model.addAttribute("rooms",new RoomPojo(room));
        model.addAttribute("booking" ,new BookPojo());
        model.addAttribute("userlog",userService.findByEmail(principal.getName()));
        return "/booking-form";
    }
        @PostMapping("/savebook")
    public String bookBike(@Valid BookPojo bookingPojo) throws IOException {
        bookingService.save(bookingPojo);
        return "payment";
    }


    @GetMapping("/booked/{id}")
    public String fetchAllbook(@PathVariable("id") Integer id, Model model , Principal principal){
        List<Book> booking= bookingService.findBookingById(id);
        model.addAttribute("books",booking);
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));

        return "MyBookings";
    }
    @GetMapping("/editbook/{id}")
    public String editBooking(@PathVariable("id") Integer id, Model model){
        Book book = bookingService.fetchById(id);
        model.addAttribute("clickeddbook", new BookPojo(book));
        return "redirect:/dest/dest";
    }
    @GetMapping("/products/{id}")
    public String getmybook(@PathVariable("id") Integer id, Model model ){
        Book book = bookingService.fetchById(id);
        model.addAttribute("bookingss", new BookPojo(book));
        model.addAttribute("clickedbook", book);
        return "editbooking";
    }

    @GetMapping("/deletes/{id}")
    public String Delbooking(@PathVariable("id")Integer id){
       bookingService.deletebyid(id);
        return "redirect:/user/index";
    }

}
