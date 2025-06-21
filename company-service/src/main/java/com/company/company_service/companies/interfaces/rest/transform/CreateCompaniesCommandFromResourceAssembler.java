package com.company.company_service.companies.interfaces.rest.transform;

import com.company.company_service.companies.domain.model.commands.CreateCompanyCommand;
import com.company.company_service.companies.interfaces.rest.resources.CreateCompaniesResource;

public class CreateCompaniesCommandFromResourceAssembler {
    public static CreateCompanyCommand toCommandFromResource(CreateCompaniesResource resource) {
        return new CreateCompanyCommand(
                resource.ruc(),
                resource.name(),
                resource.sector()
        );
    }
}
