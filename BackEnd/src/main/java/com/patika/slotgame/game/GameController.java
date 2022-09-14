package com.patika.slotgame.game;

import com.patika.slotgame.game.dto.GameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/play")
    public GameDto play(@RequestBody Game game) {
        return gameService.play(game);
    }

    @PostMapping("/cashout")
    public GameDto cashOut(@RequestBody Game game) {
        return gameService.cashOut(game);
    }

    @PostMapping("/loan")
    public GameDto loan(@RequestBody Game game) {
        return gameService.loan(game);
    }
}
