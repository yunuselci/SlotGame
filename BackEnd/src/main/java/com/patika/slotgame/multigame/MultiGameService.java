package com.patika.slotgame.multigame;

import com.patika.slotgame.error.InvalidGameException;
import com.patika.slotgame.error.NotFoundException;
import com.patika.slotgame.multigame.dto.GamePlay;
import com.patika.slotgame.multigame.dto.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.patika.slotgame.multigame.GameStatus.*;
import static com.patika.slotgame.multigame.Winner.*;

@Service
@RequiredArgsConstructor
public class MultiGameService {

    private final MultiGameRepository multiGameRepository;

    private final static int ROCK = 1;
    private final static int PAPER = 2;
    private final static int SCISSORS = 3;

    public void save(MultiGame multiGame) {
        multiGameRepository.save(multiGame);
    }

    public void update(MultiGame multiGame) {
        MultiGame inDB = multiGameRepository
                .findMultiGameByGameId(multiGame.getGameId())
                .orElseThrow(NotFoundException::new);
        multiGameRepository.save(inDB);
    }

    public MultiGame createMultiGame(Player player) {
        MultiGame multiGame = new MultiGame();
        multiGame.setGameId(UUID.randomUUID().toString());
        multiGame.setPlayer1(player.getUsername());
        multiGame.setGameStatus(NEW);
        save(multiGame);
        return multiGame;
    }

    public MultiGame connectToGame(Player player2, String gameId) {
        MultiGame multiGame = multiGameRepository
                .findMultiGameByGameId(gameId)
                .orElseThrow(NotFoundException::new);
        if (multiGame.getPlayer2() != null) {
            throw new InvalidGameException("Game is already full");
        }
        multiGame.setPlayer2(player2.getUsername());
        multiGame.setGameStatus(IN_PROGRESS);
        update(multiGame);
        return multiGame;
    }

    public MultiGame connectToRandomGame(Player player2) {
        MultiGame multiGame = multiGameRepository
                .findMultiGameByGameStatus(NEW)
                .orElseThrow(NotFoundException::new)
                .get(0);
        multiGame.setPlayer2(player2.getUsername());
        multiGame.setGameStatus(IN_PROGRESS);
        update(multiGame);
        return multiGame;
    }

    public MultiGame gamePlay(GamePlay gamePlay) {
        MultiGame multiGame = multiGameRepository
                .findMultiGameByGameId(gamePlay.getGameId())
                .orElseThrow(NotFoundException::new);
        if (gamePlay.getChoice() > 3 || gamePlay.getChoice() < 1) {
            throw new InvalidGameException("Invalid choice");
        }
        if (multiGame.getGameStatus() == FINISHED) {
            throw new InvalidGameException("Game is already finished");
        }
        if (gamePlay.getType() == PLAYER1) {
            multiGame.setChoice1(gamePlay.getChoice());
        } else {
            multiGame.setChoice2(gamePlay.getChoice());
        }
        if (multiGame.getChoice1() != 0 && multiGame.getChoice2() != 0) {
            multiGame.setGameStatus(FINISHED);
            multiGame.setWinner(checkWinner(multiGame.getChoice1(), multiGame.getChoice2()));
        }

        update(multiGame);
        return multiGame;
    }

    public Winner checkWinner(int choice1, int choice2) {
        if (choice1 == choice2) {
            return TIE;
        } else if (choice1 == ROCK && choice2 == SCISSORS) {
            return PLAYER1;
        } else if (choice1 == PAPER && choice2 == ROCK) {
            return PLAYER1;
        } else if (choice1 == SCISSORS && choice2 == PAPER) {
            return PLAYER1;
        } else {
            return PLAYER2;
        }
    }

}
