package com.system.hotel_reservation_system.repo;

import com.system.hotel_reservation_system.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {
    
}
