package com.patika.slotgame.multigame.dto;

import lombok.Data;

@Data
public class ConnectRequest {
    private Player player;
    private String gameId;
}
