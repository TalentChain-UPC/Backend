package com.company.company_service.companies.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateCompanyCommand (
        @NotBlank String name,
        @NotBlank String RUC,
        @NotBlank String sector
) {
}
