package com.company.company_service.companies.interfaces.rest.resources;

public record CompaniesResource(
        Long id,
        String ruc,
        String name,
        String sector
) {
}
