package com.gustavooarantes.authtodo.Config.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PasswordAlreadyInUseException extends RuntimeException {

    public PasswordAlreadyInUseException(String message) {
        super(message);
    }
}
