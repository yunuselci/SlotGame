package com.patika.slotgame.game;

import com.patika.slotgame.game.dto.GameDto;
import com.patika.slotgame.game.mapper.GameMapper;
import com.patika.slotgame.user.User;
import com.patika.slotgame.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    private final UserService userService;

    private final SecureRandom secureRandom = new SecureRandom();

    public List<String> roll(int credits) {
        boolean thirtyPercent = secureRandom.nextInt(100) <= 30;
        boolean sixtyPercent = secureRandom.nextInt(100) <= 60;
        ArrayList<String> symbols = new ArrayList<>();
        ArrayList<String> blocks = new ArrayList<>();
        symbols.add("cherry");
        symbols.add("lemon");
        symbols.add("orange");
        symbols.add("waterLemon");
        for (int i = 0; i < 3; i++) {
            Collections.shuffle(symbols);
            blocks.add(symbols.get(0));
        }
        boolean allTheSame = new HashSet<>(blocks).size() == 1;
        if (credits >= 40 && credits <= 60 && allTheSame && thirtyPercent) {
            blocks.clear();
            for (int i = 0; i < 3; i++) {
                Collections.shuffle(symbols);
                blocks.add(symbols.get(0));
            }
        } else if (credits > 60 && allTheSame && sixtyPercent) {
            blocks.clear();
            for (int i = 0; i < 3; i++) {
                Collections.shuffle(symbols);
                blocks.add(symbols.get(0));
            }
        }
        allTheSame = new HashSet<>(blocks).size() == 1;
        if (allTheSame) {
            switch (blocks.get(0)) {
                case "cherry" -> credits += 10;
                case "lemon" -> credits += 20;
                case "orange" -> credits += 30;
                case "waterLemon" -> credits += 40;
            }
        } else {
            credits -= 1;
        }
        blocks.add(String.valueOf(credits));
        return blocks;
    }

    public GameDto play(Game game) {
        String username = game.getUsername();
        Optional<Game> inDB = gameRepository.findByUsername(username);
        if (inDB.isEmpty()) {
            gameRepository.save(game);
        }
        User user = userService.getByUsername(username);
        int credits = user.getCredits();
        if (credits <= 0) {
            return GameMapper.INSTANCE.gameToGameDto(game, null, user);
        }
        var rollObject = roll(credits);
        credits = Integer.parseInt(rollObject.get(3));
        user.setCredits(credits);
        rollObject.remove(3);
        userService.update(username);
        return GameMapper.INSTANCE.gameToGameDto(game, rollObject, user);
    }

    public GameDto cashOut(Game game) {
        User user = userService.getByUsername(game.getUsername());
        if (user.getCredits() > 10 && user.getCredits() > user.getLoan()) {
            int payLoan = user.getCredits() - user.getLoan();
            user.setWithdraw(user.getWithdraw() + payLoan);
            user.setCredits(10);
            user.setLoan(0);
        }
        userService.update(user.getUsername());
        return GameMapper.INSTANCE.gameToGameDto(game, null, user);
    }

    public GameDto loan(Game game) {
        User user = userService.getByUsername(game.getUsername());
        user.setCredits(user.getCredits() + 50);
        user.setLoan(user.getLoan() + 50);
        userService.update(user.getUsername());
        return GameMapper.INSTANCE.gameToGameDto(game, null, user);
    }


}