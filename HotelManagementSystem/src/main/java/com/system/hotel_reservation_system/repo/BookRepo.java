package com.system.hotel_reservation_system.repo;

import com.system.hotel_reservation_system.entity.Book;
import com.system.hotel_reservation_system.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepo extends JpaRepository<Book,Integer> {
    @Query(value = "SELECT * FROM book where user_id=?1", nativeQuery = true)
    List<Book> findBookingById(Integer id);


}
