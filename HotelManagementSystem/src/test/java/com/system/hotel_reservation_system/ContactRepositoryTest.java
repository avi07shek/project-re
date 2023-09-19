package com.system.hotel_reservation_system;

import com.system.hotel_reservation_system.entity.Room;
import com.system.hotel_reservation_system.repo.RoomRepo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactRepositoryTest {

    @Autowired
    private RoomRepo roomRepo;


    @Test
    @Order(1)
    public void saveContactTest() {


        Room room = Room.builder()
                .room_type("a@g.com")
                .no_of_people(3)
                .build();

        roomRepo.save(room);

        Assertions.assertThat(room.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getContactTest() {

        Room room = Room.builder()
                .room_type("a@g.com")
                .no_of_people(3)
                .build();

        roomRepo.save(room);

        Room contactCreated = roomRepo.findById(room.getId()).get();
        Assertions.assertThat(contactCreated.getId()).isEqualTo(room.getId());

    }

//    @Test
//    @Order(3)
//    public void getListOfContactTest(){
//        Room room = Room.builder()
//                .room_type("a@g.com")
//                .no_of_people(3)
//                .build();
//
//        roomRepo.save(room);
//        List<Room> User = roomRepo.findAll();
//        Assertions.assertThat(User.size()).isGreaterThan(0);
//    }


    @Test
    @Order(4)
    public void updateContactTest(){

        Room room = Room.builder()
                .room_type("a@g.com")
                .no_of_people(3)
                .build();

        roomRepo.save(room);

        Room contact1  = roomRepo.findById(room.getId()).get();

        contact1.setRoom_type("penthouse");

        Room contactUpdated  = roomRepo.save(room);

        Assertions.assertThat(contactUpdated.getRoom_type()).isEqualTo("luxury suite");

    }

//    @Test
//    @Order(5)
//    public void deleteContactTest(){
//
//        Room room = Room.builder()
//                .room_type("a@g.com")
//                .no_of_people(3)
//                .build();
//
//        roomRepo.save(room);
//
////        @Query(value = "SELECT * from")
//
//        Room room1 = roomRepo.findById(room.getId()).get();
//        roomRepo.delete(room1);
//
//        Room room2= null;
//        Optional<Room> optionalContact = roomRepo.findby("a@g.com");
//        if(optionalContact.isPresent()){
//            room2 = optionalContact.get();
//        }
//
//        Assertions.assertThat(r).isNull();
////        Assertions.assertThat(User1.getId()).isNull();
//    }
}