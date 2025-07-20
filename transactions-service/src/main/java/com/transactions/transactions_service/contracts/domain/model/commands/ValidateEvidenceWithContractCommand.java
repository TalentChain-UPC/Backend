package com.transactions.transactions_service.contracts.domain.model.commands;

public record ValidateEvidenceWithContractCommand(
        String evidenceType,
        Long employeeId,
        String fullName,
        Long companyId,
        String data
) {
}
