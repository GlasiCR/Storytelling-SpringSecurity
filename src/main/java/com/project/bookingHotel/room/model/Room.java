package com.project.bookingHotel.room.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.bookingHotel.hotel.model.Hotel;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nameRoom;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer capacity;
    @Column(nullable = false)
    private Integer numberOfRooms;
    @Column(nullable = false)
    private Integer numberOfVacantRooms;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference
    private Hotel hotel;
}
