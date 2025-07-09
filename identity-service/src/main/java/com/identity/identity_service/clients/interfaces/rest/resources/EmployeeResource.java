package com.identity.identity_service.clients.interfaces.rest.resources;

public record EmployeeResource(
        Long id,
        Long companyId,
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
