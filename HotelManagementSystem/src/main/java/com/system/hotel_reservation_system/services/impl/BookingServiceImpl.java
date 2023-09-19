package com.system.hotel_reservation_system.services.impl;

import com.system.hotel_reservation_system.config.PasswordEncoderUtil;
import com.system.hotel_reservation_system.entity.Book;
import com.system.hotel_reservation_system.entity.Room;
import com.system.hotel_reservation_system.entity.User;
import com.system.hotel_reservation_system.pojo.BookPojo;
import com.system.hotel_reservation_system.repo.BookRepo;
import com.system.hotel_reservation_system.repo.RoomRepo;
import com.system.hotel_reservation_system.repo.UserRepo;
import com.system.hotel_reservation_system.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private  final RoomRepo roomRepo;
    private  final BookRepo bookRepo;
    private  final UserRepo userRepo;
    @Override
    public String save(BookPojo bookPojo) {
        Book book = new Book();
        if (bookPojo.getId()!= null){
            book.setId(bookPojo.getId());}
        book.setCheckin(bookPojo.getCheckin());
        book.setCheckout(bookPojo.getCheckout());
        book.setPeople(bookPojo.getPeople());
        book.setPhone(bookPojo.getPhone());
        book.setUserId(userRepo.findById(bookPojo.getUserId()).orElseThrow());
        book.setRoomId(roomRepo.findById(bookPojo.getRoomId()).orElseThrow());
        bookRepo.save(book);
        return "Created";    }

    @Override
    public List<Book> fetchAll() {
        return this.bookRepo.findAll();
    }

    @Override
    public Book fetchById(Integer id) {
        Book book=bookRepo.findById(id).orElseThrow(()->
                new RuntimeException("notfound"));
        book=Book.builder().checkout(book.getCheckin())
                .id(book.getId())
                .checkin(book.getCheckin())
                .checkout(book.getCheckout())
                .People(book.getPeople())
                .phone(book.getPhone())
                .userId(book.getUserId())
                .roomId(book.getRoomId())
                .build();
        return book;
    }

    @Override
    public void deletebyid(Integer id) {
        bookRepo.deleteById(id);
    }


    public List<Book> findAllInList(List<Book> list) {
        Stream<Book> allBooking = list.stream().map(booking ->
                Book.builder()
                        .id(booking.getId())
                        .checkin(booking.getCheckin())
                        .checkout(booking.getCheckout())
                        .People(booking.getPeople())
                        .phone(booking.getPhone())
                        .userId(booking.getUserId())
                        .roomId(booking.getRoomId())
                        .build());
        list = allBooking.toList();
        return list;
    }
    @Override
    public List findBookingById(Integer id) {
        return findAllInList(bookRepo.findBookingById(id));
    }


}
