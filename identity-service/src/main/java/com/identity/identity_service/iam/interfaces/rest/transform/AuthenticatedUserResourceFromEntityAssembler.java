package com.identity.identity_service.iam.interfaces.rest.transform;

import com.identity.identity_service.iam.domain.model.aggregates.User;
import com.identity.identity_service.iam.domain.model.entities.Role;
import com.identity.identity_service.iam.interfaces.rest.resources.AuthenticatedUserResource;

import java.util.stream.Collectors;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token){
        Long employeeId;
        Long companyId;
        if (user.getEmployee() == null && user.getCompanyId() == null){
            employeeId=0L;
            companyId=0L;
        }else {
            employeeId = user.getEmployee().getId();
            companyId = user.getCompanyId();
        }
        return new AuthenticatedUserResource(
                user.getId(),
                user.getEmail(),
                employeeId,
                companyId,
                token,
                user.getRoles().stream().map(Role::getName).collect(Collectors.toList())
        );
    }
}
