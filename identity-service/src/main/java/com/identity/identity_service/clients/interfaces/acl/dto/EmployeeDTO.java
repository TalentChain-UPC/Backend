package com.identity.identity_service.clients.interfaces.acl.dto;

public record EmployeeDTO (
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
        String area,
        Long companyId
){
}
