package com.project.bookingHotel.dtos;

import com.project.bookingHotel.enums.UserRole;

public record UserCreateDto(String login, String password, UserRole role) {
}
