package com.project.bookingHotel.repositories;

import com.project.bookingHotel.model.Hotel;
import com.project.bookingHotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository <Room, Long>{
    List<Room> findByHotelId(Long hotelId);

}
