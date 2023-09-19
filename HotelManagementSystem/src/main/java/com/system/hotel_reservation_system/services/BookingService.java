package com.system.hotel_reservation_system.services;

import com.system.hotel_reservation_system.entity.Book;
import com.system.hotel_reservation_system.pojo.BookPojo;

import java.io.IOException;
import java.util.List;

public interface BookingService {
    String save (BookPojo bookPojo) throws IOException;
    List findBookingById(Integer id);
    List<Book> fetchAll();
    Book fetchById(Integer id);

    void deletebyid(Integer id);

}
