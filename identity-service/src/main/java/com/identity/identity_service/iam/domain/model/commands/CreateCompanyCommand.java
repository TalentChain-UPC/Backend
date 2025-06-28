package com.identity.identity_service.iam.domain.model.commands;

public record CreateCompanyCommand(
        String name,
        String ruc,
        String sector
) {
}
