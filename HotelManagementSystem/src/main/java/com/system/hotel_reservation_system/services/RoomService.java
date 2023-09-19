package com.system.hotel_reservation_system.services;

import com.system.hotel_reservation_system.entity.Room;
import com.system.hotel_reservation_system.pojo.RoomPojo;

import java.io.IOException;
import java.util.List;

public interface RoomService {

    String saveRoom(RoomPojo roomPojo) throws IOException;

    List<Room> fetchAll();

    Room fetchById(Integer id);

    void deletebyid(Integer id);
}
