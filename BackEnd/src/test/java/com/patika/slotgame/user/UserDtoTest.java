package com.patika.slotgame.user;

import com.patika.slotgame.user.dto.UserDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserDtoTest {

    @Test
    void allArgsConstructorShouldWork(){
        UserDto user = new UserDto("Yunus", 100, 20, 20);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("Yunus");
        assertThat(user.getCredits()).isEqualTo(100);
        assertThat(user.getLoan()).isEqualTo(20);
        assertThat(user.getWithdraw()).isEqualTo(20);
    }

    @Test
    void setFieldShouldWork(){
        UserDto user = new UserDto();
        user.setUsername("Yunus");
        user.setCredits(100);
        user.setLoan(20);
        user.setWithdraw(20);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("Yunus");
        assertThat(user.getCredits()).isEqualTo(100);
        assertThat(user.getLoan()).isEqualTo(20);
        assertThat(user.getWithdraw()).isEqualTo(20);
    }

    @Test
    void toStringShouldWork(){
        UserDto user = new UserDto("Yunus", 100, 20, 20);
        assertThat(user.toString()).hasToString("UserDto(username=Yunus, credits=100, loan=20, withdraw=20)");
    }

    @Test
    void toHashCodeShouldWork(){
        UserDto user = new UserDto("Yunus", 100, 20, 20);
        assertThat(user.hashCode()).hasSameHashCodeAs(user.hashCode());
    }

    @Test
    void equalsShouldWork(){
        UserDto user = new UserDto("Yunus", 100, 20, 20);
        UserDto user2 = new UserDto("Yunus", 100, 20, 20);
        assertThat(user.equals(user2)).isTrue();
    }

}
