package com.gustavooarantes.authtodo.Config.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {super(message);}
}
