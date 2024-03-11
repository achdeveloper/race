package org.example.race.config;

import org.example.race.data.dto.ErrorMessage;
import org.example.race.exception.InsufficientRunnersException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class HandlerException {


    @ExceptionHandler(InsufficientRunnersException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(InsufficientRunnersException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
