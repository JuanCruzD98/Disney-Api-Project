package com.disneyapiproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmailIsAlreadyTakenException extends RuntimeException {
    public static final long serialVersionUID = 1L;
    private String message;


    public EmailIsAlreadyTakenException(String message) {
        super(message);
        this.message = getMessage();

    }

    public String getMessage() {
        return message;
    }
}


