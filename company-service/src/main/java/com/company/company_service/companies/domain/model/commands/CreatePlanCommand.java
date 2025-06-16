package com.company.company_service.companies.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreatePlanCommand(
        @NotBlank String name,
        @NotBlank Double price_per_employ,
        @NotBlank Integer max_employees,
        @NotBlank String description
) {
}
