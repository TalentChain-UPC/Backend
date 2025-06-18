package com.identity.identity_service.clients.domain.model.commands;

public record CreateEmployeeCommand(
        String name,
        String lastName,
        String age,
        String dni,
        String gender,
        String location,
        String phoneNumber,
        String workEmail,
        String personalEmail,
        String occupation,
        String area
) {
}
