package com.project.bookingHotel.room.controllers;

import com.project.bookingHotel.room.model.Room;
import com.project.bookingHotel.room.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    /*@PostMapping("/{idHotel}")
    public Room createRoom(@PathVariable(value = "idHotel") Long idHotel, @RequestBody Room room) {
        return roomService.createRoom(idHotel, room);
    }*/
}
