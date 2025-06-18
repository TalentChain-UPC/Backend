package com.gateway.gateway_service.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

    @ExceptionHandler(JwtNotFoundException.class)
    public ResponseEntity<MessageResource> handle(JwtNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new MessageResource(ex.getMessage()));
    }

    @ExceptionHandler(AuthorizationHeaderNotFoundException.class)
    public ResponseEntity<MessageResource> handle(AuthorizationHeaderNotFoundException ex){
        LOGGER.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new MessageResource(ex.getMessage()));
    }
}
