package com.company.company_service.companies.interfaces.rest.transform;

import com.company.company_service.companies.domain.model.entities.Plans;
import com.company.company_service.companies.interfaces.rest.resources.PlansResource;

public class PlansResourceFromEntityAssembler {
    public static PlansResource transformResourceFromEntity(Plans plans) {
        return new PlansResource(
                plans.getId(),
                plans.getName(),
                plans.getPrice_per_employ(),
                plans.getMax_employees(),
                plans.getDescription()
        );
    }
}
