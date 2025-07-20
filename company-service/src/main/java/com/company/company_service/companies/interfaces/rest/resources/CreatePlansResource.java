package com.company.company_service.companies.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record CreatePlansResource(
        @NotBlank String name,
        @NotBlank Double price_per_employ,
        @NotBlank Integer max_employees,
        @NotBlank String description
) {
}
