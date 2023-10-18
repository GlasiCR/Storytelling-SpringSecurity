package com.project.bookingHotel.controllers;

import com.project.bookingHotel.dtos.RoomCreateDto;
import com.project.bookingHotel.model.Room;
import com.project.bookingHotel.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/")
    public ResponseEntity<Room> createRoom(@RequestBody @Valid RoomCreateDto room) {
        return roomService.createRoom(room);
    }

    @GetMapping("/{hotelId}")
    public List<Room> getRoomsByHotelId(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotelId(hotelId);
    }
}
