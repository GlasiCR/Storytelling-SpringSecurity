package com.project.bookingHotel.hotel.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.bookingHotel.room.model.Room;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String descriptionHotel;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Room> rooms;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private Integer numberOfRooms;
    @Column(nullable = false)
    private Integer numberOfRoomsAvaiable;
    @Column
    private Boolean thereIsRoomAvaiable = true;
    @CreationTimestamp
    @Column(name="createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name="updatedAt", nullable = false)
    private LocalDateTime updatedAt;

}


