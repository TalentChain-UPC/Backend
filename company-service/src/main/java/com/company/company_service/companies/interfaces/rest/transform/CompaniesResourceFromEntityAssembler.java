package com.company.company_service.companies.interfaces.rest.transform;

import com.company.company_service.companies.domain.model.entities.Companies;
import com.company.company_service.companies.interfaces.rest.resources.CompaniesResource;

public class CompaniesResourceFromEntityAssembler {
    public static CompaniesResource transformResourceFromEntity(Companies companies) {
        return new CompaniesResource(
                companies.getId(),
                companies.getRuc(),
                companies.getName(),
                companies.getSector()
        );
    }
}
