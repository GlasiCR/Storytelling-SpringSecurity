package com.project.bookingHotel.services;

import com.project.bookingHotel.dtos.RoomCreateDto;
import com.project.bookingHotel.model.Hotel;
import com.project.bookingHotel.model.Room;
import com.project.bookingHotel.repositories.HotelRepository;
import com.project.bookingHotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;


    public ResponseEntity<Room> createRoom(RoomCreateDto room){
        Optional<Hotel> hotelAlready = hotelRepository.findById(room.idHotel());
        if(hotelAlready.isPresent()){
            Hotel hotel = hotelAlready.get();
            Room newRoom = new Room();
            newRoom.setNameRoom(room.nameRoom());
            newRoom.setPrice(room.price());
            newRoom.setCapacity(room.capacity());
            newRoom.setNumberOfRooms(room.numberOfRooms());
            newRoom.setNumberOfVacantRooms(room.numberOfVacantRooms());
            newRoom.setHotel(hotel);
            roomRepository.save(newRoom);

            hotel.getRooms().add(newRoom);
            hotelRepository.saveAndFlush(hotel);

            return ResponseEntity.ok().body(newRoom);
        }
        return ResponseEntity.notFound().build();
    }

    public List<Room> getRoomsByHotelId(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }
}
