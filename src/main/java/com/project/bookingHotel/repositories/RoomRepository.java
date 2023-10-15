package com.project.bookingHotel.repositories;

import com.project.bookingHotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository <Room, Long>{
  /*public Room saveRoomInHotel(Hotel hotel, Room room) {
    }*/
}
