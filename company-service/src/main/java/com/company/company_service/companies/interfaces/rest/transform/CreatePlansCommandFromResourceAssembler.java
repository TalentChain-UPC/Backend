package com.company.company_service.companies.interfaces.rest.transform;

import com.company.company_service.companies.domain.model.commands.CreatePlanCommand;
import com.company.company_service.companies.interfaces.rest.resources.CreatePlansResource;

public class CreatePlansCommandFromResourceAssembler {
    public static CreatePlanCommand toCommandFromResource(CreatePlansResource resource) {
        return new CreatePlanCommand(
                resource.name(),
                resource.price_per_employ(),
                resource.max_employees(),
                resource.description()
        );
    }
}