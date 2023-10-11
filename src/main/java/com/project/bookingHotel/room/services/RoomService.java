package com.project.bookingHotel.room.services;

import com.project.bookingHotel.hotel.model.Hotel;
import com.project.bookingHotel.hotel.repositories.HotelRepository;
import com.project.bookingHotel.room.model.Room;
import com.project.bookingHotel.room.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;


    /*public Room createRoom(Long idHotel, Room room){
        Optional<Hotel> hotelAlready = hotelRepository.findById(idHotel);
        if(hotelAlready.isPresent()){
            Hotel hotel = hotelAlready.get();
            return roomRepository.saveRoomInHotel(hotel, room);
        }
        throw new IllegalArgumentException("Dados do hotel inválidos");
    }*/

}
