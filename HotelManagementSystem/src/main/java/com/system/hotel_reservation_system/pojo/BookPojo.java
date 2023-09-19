package com.system.hotel_reservation_system.pojo;

import com.system.hotel_reservation_system.entity.Book;
import com.system.hotel_reservation_system.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookPojo {
private  Integer id;
private  String checkin;
private  String checkout;
private  String people;
private  String phone;
private  int roomId;
private  int userId;

    public BookPojo(Book book){
        this.id= book.getId();
        this.checkout= book.getCheckout();
        this.checkin= book.getCheckin();
        this.people= book.getPeople();
        this.roomId= book.getRoomId().getId();
        this.userId= book.getUserId().getId();
        this.phone= book.getPhone();
    }

}
