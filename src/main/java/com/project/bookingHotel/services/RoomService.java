package com.project.bookingHotel.services;

import com.project.bookingHotel.repositories.HotelRepository;
import com.project.bookingHotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
