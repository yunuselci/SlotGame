package com.patika.slotgame.multigame;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultiGame {
    @Id
    private String gameId;

    private String player1;

    private String player2;

    private int choice1;

    private int choice2;

    private GameStatus gameStatus;

    private Winner winner;
}
