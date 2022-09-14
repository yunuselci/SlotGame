package com.patika.slotgame.game.mapper;

import com.patika.slotgame.game.Game;
import com.patika.slotgame.game.dto.GameDto;
import com.patika.slotgame.user.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GameMapper {
    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);
    @InheritInverseConfiguration
    @Mapping(target = "userCredits", source = "user.credits")
    @Mapping(target = "loan", source = "user.loan")
    @Mapping(target = "withdraw", source = "user.withdraw")
    GameDto gameToGameDto(Game game, List<String> slots, User user);
}
