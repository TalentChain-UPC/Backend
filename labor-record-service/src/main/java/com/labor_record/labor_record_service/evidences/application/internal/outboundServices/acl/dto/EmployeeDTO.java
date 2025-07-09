package com.labor_record.labor_record_service.evidences.application.internal.outboundServices.acl.dto;

public record EmployeeDTO(
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
