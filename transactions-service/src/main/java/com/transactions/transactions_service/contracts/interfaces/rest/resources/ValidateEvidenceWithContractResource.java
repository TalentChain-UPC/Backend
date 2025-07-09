package com.transactions.transactions_service.contracts.interfaces.rest.resources;

public record ValidateEvidenceWithContractResource(
        String evidenceType,
        Long employeeId,
        String fullName,
        Long companyId,
        String data
) {
}
