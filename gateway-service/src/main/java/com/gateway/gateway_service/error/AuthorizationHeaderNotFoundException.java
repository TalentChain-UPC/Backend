package com.gateway.gateway_service.error;

public class AuthorizationHeaderNotFoundException extends RuntimeException {
    public AuthorizationHeaderNotFoundException() {
        super("The 'Authorization' header is required but was not provided.");
    }
}
