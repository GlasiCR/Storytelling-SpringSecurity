package com.project.bookingHotel.room.repositories;

import com.project.bookingHotel.hotel.model.Hotel;
import com.project.bookingHotel.room.model.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository <Room, Long>{
  /*public Room saveRoomInHotel(Hotel hotel, Room room) {
    }*/
}
