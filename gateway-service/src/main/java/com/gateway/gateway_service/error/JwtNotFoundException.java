package com.gateway.gateway_service.error;

public class JwtNotFoundException extends RuntimeException {
    public JwtNotFoundException() {
        super("The provided JWT token is invalid or cannot be verified. Please provide a valid token.");
    }
}
