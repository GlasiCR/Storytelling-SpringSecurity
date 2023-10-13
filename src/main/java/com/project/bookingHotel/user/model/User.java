package com.project.bookingHotel.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 10)
    private String password;
    /* @Column
     private List<Booking> booking;*/
    @CreationTimestamp
    @Column(name="createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    public User(String name, String email, String password) {
    }
}