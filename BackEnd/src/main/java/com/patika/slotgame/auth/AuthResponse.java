package com.patika.slotgame.auth;

import com.patika.slotgame.user.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private UserDto user;
}
