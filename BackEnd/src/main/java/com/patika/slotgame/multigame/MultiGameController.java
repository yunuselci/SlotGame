package com.patika.slotgame.multigame;

import com.patika.slotgame.multigame.dto.ConnectRequest;
import com.patika.slotgame.multigame.dto.GamePlay;
import com.patika.slotgame.multigame.dto.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/multi/")
@RequiredArgsConstructor
public class MultiGameController {

    private final MultiGameService multiGameService;

    @PostMapping("/create")
    public MultiGame createMultiGame(@RequestBody Player player) {
        return multiGameService.createMultiGame(player);
    }

    @PostMapping("/connect")
    public MultiGame connectToGame(@RequestBody ConnectRequest connectRequest) {
        return multiGameService.connectToGame(connectRequest.getPlayer(), connectRequest.getGameId());
    }

    @PostMapping("/connect/random")
    public MultiGame connectToRandomGame(@RequestBody Player player) {
        return multiGameService.connectToRandomGame(player);
    }

    @PostMapping("/play")
    public MultiGame play(@RequestBody GamePlay gamePlay) {
        return multiGameService.gamePlay(gamePlay);
    }
}