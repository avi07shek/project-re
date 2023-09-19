package com.system.hotel_reservation_system.pojo;


import com.system.hotel_reservation_system.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomPojo {
    private Integer id;









    private Double price;


    private String room_description;


    private String room_type;





    private Integer no_of_people;

    private MultipartFile image1;

    private MultipartFile image2;

    private MultipartFile image3;

    private MultipartFile image4;

    private MultipartFile image5;

    private String beds;

    public RoomPojo(Room room){
        this.id = room.getId();



        this.price = room.getPrice();
        this.room_description = room.getRoom_description();
        this.room_type = room.getRoom_type();

        this.no_of_people = room.getNo_of_people();
        this.beds=room.getBeds();


    }


}