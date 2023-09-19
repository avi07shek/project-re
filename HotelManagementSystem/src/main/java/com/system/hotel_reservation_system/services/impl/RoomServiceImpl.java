package com.system.hotel_reservation_system.services.impl;

import com.system.hotel_reservation_system.entity.Room;
import com.system.hotel_reservation_system.pojo.RoomPojo;
import com.system.hotel_reservation_system.repo.RoomRepo;
import com.system.hotel_reservation_system.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepo  RoomRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/hotel_mgmt/";


    @Override
    public String saveRoom(RoomPojo RoomPojo) throws IOException {
        Room room = new Room();
        if (RoomPojo.getId()!= null){    room.setId(RoomPojo.getId());}

        room.setPrice(RoomPojo.getPrice());
        room.setRoom_description(RoomPojo.getRoom_description());
        room.setRoom_type(RoomPojo.getRoom_type());
        room.setNo_of_people(RoomPojo.getNo_of_people());
        room.setBeds(RoomPojo.getBeds());

        if(!Objects.equals(RoomPojo.getImage1().getOriginalFilename(), "")){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, RoomPojo.getImage1().getOriginalFilename());
            fileNames.append(RoomPojo.getImage1().getOriginalFilename());
            Files.write(fileNameAndPath, RoomPojo.getImage1().getBytes());
            room.setImage1(RoomPojo.getImage1().getOriginalFilename());
        }

        if(!Objects.equals(RoomPojo.getImage2().getOriginalFilename(), "")){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, RoomPojo.getImage2().getOriginalFilename());
            fileNames.append(RoomPojo.getImage2().getOriginalFilename());
            Files.write(fileNameAndPath, RoomPojo.getImage2().getBytes());
            room.setImage2(RoomPojo.getImage2().getOriginalFilename());
        }

        if(!Objects.equals(RoomPojo.getImage3().getOriginalFilename(), "")){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, RoomPojo.getImage3().getOriginalFilename());
            fileNames.append(RoomPojo.getImage3().getOriginalFilename());
            Files.write(fileNameAndPath, RoomPojo.getImage3().getBytes());
            room.setImage3(RoomPojo.getImage3().getOriginalFilename());
        }

        if(!Objects.equals(RoomPojo.getImage4().getOriginalFilename(), "")){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, RoomPojo.getImage4().getOriginalFilename());
            fileNames.append(RoomPojo.getImage4().getOriginalFilename());
            Files.write(fileNameAndPath, RoomPojo.getImage4().getBytes());
            room.setImage4(RoomPojo.getImage4().getOriginalFilename());
        }

        if(!Objects.equals(RoomPojo.getImage5().getOriginalFilename(), "")){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, RoomPojo.getImage5().getOriginalFilename());
            fileNames.append(RoomPojo.getImage5().getOriginalFilename());
            Files.write(fileNameAndPath, RoomPojo.getImage5().getBytes());
            room.setImage5(RoomPojo.getImage5().getOriginalFilename());
        }

        RoomRepo.save(room);
        return "Created";

    }

    public String getImageBase64(String fileName) {
        if (fileName!=null) {
            String filePath = System.getProperty("user.dir")+"\\hotel_mgmt\\";
            File file = new File(filePath + fileName);
            byte[] bytes;
            try {
                bytes = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return Base64.getEncoder().encodeToString(bytes);
        }
        return null;
    }

    public List<Room> listMapping(List<Room> list){
        Stream<Room> allRoomsWithImage=list.stream().map(room ->
                Room.builder()
                        .id(room.getId())

                        .price(room.getPrice())
                        .beds(room.getBeds())
                        .image1Base64(getImageBase64(room.getImage1()))
                        .image2Base64(getImageBase64(room.getImage2()))
                        .image3Base64(getImageBase64(room.getImage3()))
                        .image4Base64(getImageBase64(room.getImage4()))
                        .image5Base64(getImageBase64(room.getImage5()))
                        .room_description(room.getRoom_description())
                        .room_type(room.getRoom_type())
                        .no_of_people(room.getNo_of_people())
                        .build()
        );

        list = allRoomsWithImage.toList();
        return list;
    }


    @Override
    public List<Room> fetchAll() {
        return listMapping(RoomRepo.findAll());
    }

    @Override
    public Room fetchById(Integer id) {
        Room room =RoomRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
        room=Room.builder()
                .id(room.getId())
                .price(room.getPrice())
                .beds(room.getBeds())
                .image1Base64(getImageBase64(room.getImage1()))
                .image2Base64(getImageBase64(room.getImage2()))
                .image3Base64(getImageBase64(room.getImage3()))
                .image4Base64(getImageBase64(room.getImage4()))
                .image5Base64(getImageBase64(room.getImage5()))
                .room_description(room.getRoom_description())
                .room_type(room.getRoom_type())
                .no_of_people(room.getNo_of_people())

                .build();
        return room;
    }

    @Override
    public void deletebyid(Integer id) {
        RoomRepo.deleteById(id);
    }
}
