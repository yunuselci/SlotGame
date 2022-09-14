package com.patika.slotgame.multigame.dto;

import com.patika.slotgame.multigame.Winner;
import lombok.Data;

@Data
public class GamePlay {
    private Winner type;
    private int choice;
    private String gameId;
    private String winner;
}
