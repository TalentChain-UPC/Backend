package com.identity.identity_service.iam.domain.model.commands;

public record SignUpEmployeeCommand(
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
