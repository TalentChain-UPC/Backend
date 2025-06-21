package com.company.company_service.companies.interfaces.rest.resources;

public record PlansResource(
        Long id,
        String name,
        Double price_per_employ,
        Integer max_employees,
        String description
) {
}
