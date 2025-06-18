package com.identity.identity_service.clients.interfaces.rest.transform;

import com.identity.identity_service.clients.domain.model.aggregates.Employee;
import com.identity.identity_service.clients.interfaces.rest.resources.EmployeeResource;

public class EmployeeResourceFromEntityAssembler {
    public static EmployeeResource toResourceFromEntity(Employee entity){
        return new EmployeeResource(
                entity.getId(),
                entity.getFullName().name(),
                entity.getFullName().lastName(),
                entity.getIdentity().age(),
                entity.getIdentity().dni(),
                entity.getIdentity().gender(),
                entity.getIdentity().location(),
                entity.getContactInfo().phoneNumber(),
                entity.getContactInfo().workEmail(),
                entity.getContactInfo().personalEmail(),
                entity.getOccupation(),
                entity.getArea().name()
        );
    }
}
