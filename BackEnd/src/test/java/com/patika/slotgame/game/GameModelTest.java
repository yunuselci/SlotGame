package com.patika.slotgame.game;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameModelTest {

    @Test
    void allArgsConstructorShouldWork() {
        Game game = new Game(1L, "Yunus");
        assertThat(game).isNotNull();
        assertThat(game.getId()).isEqualTo(1L);
        assertThat(game.getUsername()).isEqualTo("Yunus");
    }

    @Test
    void setFieldShouldWork() {
        Game game = new Game();
        game.setId(1L);
        game.setUsername("Yunus");
        assertThat(game).isNotNull();
        assertThat(game.getId()).isEqualTo(1L);
        assertThat(game.getUsername()).isEqualTo("Yunus");
    }

    @Test
    void builderShouldWork() {
        Game game = Game.builder()
                .id(1L)
                .username("Yunus")
                .build();
        assertThat(game).isNotNull();
        assertThat(game.getId()).isEqualTo(1L);
        assertThat(game.getUsername()).isEqualTo("Yunus");
    }

    @Test
    void toStringMethodShouldWork() {
        Game game = new Game(1L,"Yunus");
        assertThat(game.toString()).hasToString("Game(id=1, username=Yunus)");
    }

    @Test
    void toHashCodeMethodShouldWork() {
        Game game = new Game(1L, "Yunus");
        assertThat(game.hashCode()).hasSameHashCodeAs(game.hashCode());
    }

    @Test
    void equalsShouldWork() {
        Game game = new Game(1L, "Yunus");
        Game game2 = new Game(1L,"Yunus");
        assertThat(game.equals(game2)).isTrue();
        assertThat(game.canEqual(game2)).isTrue();
    }


}
