package com.identity.identity_service.iam.interfaces.rest.transform;

import com.identity.identity_service.iam.domain.model.commands.SignUpEmployeeCommand;
import com.identity.identity_service.iam.interfaces.rest.resources.RegisterEmployeeResource;

public class SignUpEmployeeCommandFromResourceAssembler {
    public static SignUpEmployeeCommand toCommandFromResource(RegisterEmployeeResource resource){
        return new SignUpEmployeeCommand(
                resource.name(),
                resource.lastName(),
                resource.age(),
                resource.dni(),
                resource.gender(),
                resource.location(),
                resource.phoneNumber(),
                resource.workEmail(),
                resource.personalEmail(),
                resource.occupation(),
                resource.area()
        );
    }
}
