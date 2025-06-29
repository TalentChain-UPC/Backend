package com.identity.identity_service.iam.domain.model.commands;

public record CreateCompanyCommand(
        String ruc,
        String name,
        String sector
) {
}
