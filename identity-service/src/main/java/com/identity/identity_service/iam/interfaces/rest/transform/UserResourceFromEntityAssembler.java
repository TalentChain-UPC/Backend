package com.identity.identity_service.iam.interfaces.rest.transform;

import com.identity.identity_service.iam.domain.model.aggregates.User;
import com.identity.identity_service.iam.domain.model.entities.Role;
import com.identity.identity_service.iam.interfaces.rest.resources.AuthenticatedUserResource;

import java.util.stream.Collectors;

public class UserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token){
        Long employeeId=0L;
        if (user.getEmployee() == null){
            employeeId=0L;
        }else {
            employeeId = user.getEmployee().getId();
        }
        return new AuthenticatedUserResource(
                user.getId(),
                user.getEmail(),
                employeeId,
                token,
                user.getRoles().stream().map(Role::getName).collect(Collectors.toList())
        );
    }
}
