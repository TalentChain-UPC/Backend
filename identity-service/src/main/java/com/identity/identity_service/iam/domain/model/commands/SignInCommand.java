package com.identity.identity_service.iam.domain.model.commands;

public record SignInCommand(String email, String password) {
}
