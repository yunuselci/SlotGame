package com.patika.slotgame.user;

import com.patika.slotgame.user.dto.UserDto;
import com.patika.slotgame.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    @Test
    void shouldMapUserToUserDto() {

        User user = User.builder()
                .id(1L)
                .username("Yunus")
                .credits(100)
                .loan(20)
                .withdraw(20)
                .build();

        UserDto userDTO = UserMapper.INSTANCE.userToUserDto(user);

        assertThat(userDTO).isNotNull();
        assertThat(userDTO.getUsername()).isEqualTo("Yunus");
        assertThat(userDTO.getCredits()).isEqualTo(100);
        assertThat(userDTO.getLoan()).isEqualTo(20);
        assertThat(userDTO.getWithdraw()).isEqualTo(20);

    }
}
