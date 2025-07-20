package com.identity.identity_service.shared.infrastructure.exceptionHandler;

import com.identity.identity_service.iam.domain.exceptions.IncorrectPasswordException;
import com.identity.identity_service.iam.domain.exceptions.UserAlreadyExistsException;
import com.identity.identity_service.iam.domain.exceptions.UserNotFoundException;
import com.identity.identity_service.shared.interfaces.rest.resource.MessageResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageResource> handle(UserNotFoundException ex){
        LOGGER.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new MessageResource(ex.getMessage()));
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<MessageResource> handle(IncorrectPasswordException ex){
        LOGGER.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource(ex.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<MessageResource> handle(UserAlreadyExistsException ex){
        LOGGER.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource(ex.getMessage()));
    }
}
