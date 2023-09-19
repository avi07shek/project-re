package com.system.hotel_reservation_system.controller;

import com.system.hotel_reservation_system.entity.Book;
import com.system.hotel_reservation_system.entity.Review;
import com.system.hotel_reservation_system.entity.Room;
import com.system.hotel_reservation_system.pojo.RoomPojo;
import com.system.hotel_reservation_system.services.BookingService;
import com.system.hotel_reservation_system.services.RoomService;
import com.system.hotel_reservation_system.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final RoomService roomService;
    private final UserService userService;
    private final BookingService bookingService;

    @GetMapping("/add")
    public String getRegister(Model model) {
        model.addAttribute("room", new RoomPojo());
        return "addRoom";
    }

    @PostMapping("/create")
    public String createRoom(@Valid RoomPojo roomPojo,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            System.out.println(requestError);
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/add";
        }
        roomService.saveRoom(roomPojo);
        redirectAttributes.addFlashAttribute("successMsg", "Room saved successfully");

        return "redirect:admin/add";
    }

    public Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;

    }
    @GetMapping("/roomlist")
    public String GetRoomlist(Model model){
        List<Room> rooms = roomService.fetchAll();
//        model.addAttribute("roomlist", rooms.stream().map(room ->
//                Room.builder()
//                        .room_type(room.getRoom_type())
//                        .price(room.getPrice())
//                        .beds(room.getBeds())
//                        .build()
//
//        ));
        model.addAttribute("roomData",rooms);
        return  "room-list";
    }
    @GetMapping("/deletes/{id}")
    public String DelRoom(@PathVariable("id")Integer id){
        roomService.deletebyid(id);
        return "redirect:/admin/roomlist";
    }

    @GetMapping("/editroom/{id}")
    public String EditRoom(@PathVariable("id") Integer id,Model model){
        Room room=roomService.fetchById(id);
        model.addAttribute("erooms",new RoomPojo(room));
        model.addAttribute("edrooms",room);
        return "editroom";
    }

    @GetMapping("/editrooms/{id}")
    public String EditRooms(@PathVariable("id") Integer id,Model model){
        Room room=roomService.fetchById(id);
        model.addAttribute("edrooms",new RoomPojo(room));
        return "redirect:admin/roomlist";
    }
    @GetMapping("/reviews")
    public String GetRevs(Model model) {
        List<Review> reviews = userService.fetchAll();
        model.addAttribute("revData", reviews);
        return "reviews";
    }
    @GetMapping("/dash")
    public String GetDash(){
        return "/Dashboard";
    }

    @GetMapping("/boook")
    public String GetBook(Model model) {
        List<Book> book = bookingService.fetchAll();
        model.addAttribute("bookinglist", book.stream().map(books->
                Book.builder()
                        .id(books.getId())
                        .checkin(books.getCheckin())
                        .checkout(books.getCheckout())
                        .phone(books.getPhone())
                        .People(books.getPeople())
                        .userId(books.getUserId())
                        .roomId(books.getRoomId())
                        .build()
        ));
        return "guest-list";
    }
}
