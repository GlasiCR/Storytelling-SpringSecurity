package com.project.bookingHotel.hotel.service;

import com.project.bookingHotel.hotel.model.Hotel;
import com.project.bookingHotel.hotel.repositories.HotelRepository;
import com.project.bookingHotel.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public Hotel create(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAllHotels(){
        return hotelRepository.findAll();
    }

    /*public List<Hotel> getHotelByFilter(String query){
        return hotelRepository.findAll(query);
    }*/

    public ResponseEntity<Hotel> findByCity(String city) {
        return hotelRepository.findByCity(city)
                .map(hotel -> ResponseEntity.ok().body(hotel))
                .orElse(ResponseEntity.notFound().build());
    }

    public List<Hotel> findByNameStartingWith(String name){
        return hotelRepository.findByNameStartingWith(name);
    }

    public ResponseEntity<?> findDescriptionHotelById(Long id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);

        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            Map<String, String> detailsHotel = new HashMap<>();
            detailsHotel.put("name", hotel.getName());
            detailsHotel.put("description", hotel.getDescriptionHotel());
            return ResponseEntity.ok().body(detailsHotel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


    /*

    mostrar hoteis de acordo com o filtro
    quando clicar no hotel deve permitir visualizar informações como: descrição, comodidades e preços dos quartos

     */

