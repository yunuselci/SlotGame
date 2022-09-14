package com.patika.slotgame.error;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@AllArgsConstructor
@NoArgsConstructor
public class InvalidGameException extends RuntimeException {
    private String message;
}
