package com.project.bookingHotel.repositories;
import com.project.bookingHotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("SELECT c FROM Hotel c WHERE c.city = :city AND c.numberOfRoomsAvaiable > 0")
    Optional<Hotel> findByCity(String city);
    List<Hotel> findByNameStartingWith(String name);
}
