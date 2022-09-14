package com.patika.slotgame.security;

import com.patika.slotgame.auth.AuthResponse;
import com.patika.slotgame.user.dto.UserDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthResponseModelTest {

    AuthResponse authResponse = new AuthResponse();

    @Test
    void setFieldShouldWork() {
        authResponse.setToken("Test, message.");
        authResponse.setUser(new UserDto());
        assertThat(authResponse).isNotNull();
        assertThat(authResponse.getToken()).isEqualTo("Test, message.");
        assertThat(authResponse.getUser()).isNotNull();
    }

    @Test
    void toStringMethodShouldWork() {
        authResponse.setToken("Test, message.");
        authResponse.setUser(new UserDto());
        assertThat(authResponse.toString()).hasToString("AuthResponse(token=Test, message., user=UserDto(username=null, credits=0, loan=0, withdraw=0))");
    }

    @Test
    void toHashCodeMethodShouldWork() {
        authResponse.setToken("Test, message.");
        authResponse.setUser(new UserDto());
        assertThat(authResponse.hashCode()).hasSameHashCodeAs(authResponse.hashCode());
    }

    @Test
    void equalsShouldWork() {
        AuthResponse authResponse1 = new AuthResponse();
        authResponse.setToken("Test, message.");
        authResponse.setUser(new UserDto());
        authResponse1.setToken("Test, message.");
        authResponse1.setUser(new UserDto());
        assertThat(authResponse.equals(authResponse1)).isTrue();
    }
}
