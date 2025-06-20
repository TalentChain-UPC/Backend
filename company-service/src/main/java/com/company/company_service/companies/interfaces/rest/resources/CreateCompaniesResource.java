package com.company.company_service.companies.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record CreateCompaniesResource(
        @NotBlank String ruc,
        @NotBlank String name,
        @NotBlank String sector
) {
}
