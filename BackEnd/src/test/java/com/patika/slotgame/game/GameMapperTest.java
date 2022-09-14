package com.patika.slotgame.game;

import com.patika.slotgame.game.dto.GameDto;
import com.patika.slotgame.game.mapper.GameMapper;
import com.patika.slotgame.user.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameMapperTest {
    @Test
    void shouldMapGameToDto() {

        Game game = Game.builder()
                .id(1L)
                .username("Yunus")
                .build();

        User user = User.builder()
                .id(1L)
                .username("Yunus")
                .credits(100)
                .loan(20)
                .withdraw(20)
                .build();

        GameDto gameDTO = GameMapper.INSTANCE.gameToGameDto(game, null, user);

        assertThat(gameDTO).isNotNull();
        assertThat(gameDTO.getUserCredits()).isEqualTo(100);
        assertThat(gameDTO.getLoan()).isEqualTo(20);
        assertThat(gameDTO.getWithdraw()).isEqualTo(20);

    }
}
