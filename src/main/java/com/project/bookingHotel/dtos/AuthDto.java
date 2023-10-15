package com.project.bookingHotel.dtos;

import com.project.bookingHotel.enums.UserRole;

public record AuthDto(String login, String password, UserRole role) {
}
