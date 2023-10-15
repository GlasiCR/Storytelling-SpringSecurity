package com.project.bookingHotel.controllers;

import com.project.bookingHotel.services.RoomService;
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
