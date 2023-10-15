package com.project.bookingHotel.controllers;

import com.project.bookingHotel.model.Hotel;
import com.project.bookingHotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
  @Autowired
    private HotelService hotelService;
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel create(@RequestBody Hotel hotel) {
        return hotelService.create(hotel);
    }

    @GetMapping("/location")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Hotel> findByCity(@RequestParam String city){
      return hotelService.findByCity(city);
    }

    @GetMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public List<Hotel> findByNameStartingWith(@RequestParam String name) {
      return hotelService.findByNameStartingWith(name);
    }

    @GetMapping("/{id}/details")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findDescriptionHotelById(@PathVariable(value = "id") Long id){
      return hotelService.findDescriptionHotelById(id);
    }
}
