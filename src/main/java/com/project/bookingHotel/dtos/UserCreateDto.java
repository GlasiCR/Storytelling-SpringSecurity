package com.project.bookingHotel.dtos;

import com.project.bookingHotel.enums.UserRole;

public record UserCreateDto(String name, String email, String password, UserRole role) {
}

