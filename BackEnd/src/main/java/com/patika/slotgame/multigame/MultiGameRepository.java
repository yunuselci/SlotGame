package com.patika.slotgame.multigame;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MultiGameRepository extends JpaRepository<MultiGame, String> {
    Optional<MultiGame> findMultiGameByGameId(String gameId);
    Optional<List<MultiGame>> findMultiGameByGameStatus(GameStatus gameStatus);

}
