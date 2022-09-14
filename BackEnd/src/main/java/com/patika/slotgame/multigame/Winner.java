package com.patika.slotgame.multigame;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Winner {
    PLAYER1(1), PLAYER2(2), TIE(3);

    private final Integer value;

}
