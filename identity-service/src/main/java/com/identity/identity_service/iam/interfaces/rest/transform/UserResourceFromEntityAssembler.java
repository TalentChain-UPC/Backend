package com.identity.identity_service.iam.interfaces.rest.transform;

import com.identity.identity_service.iam.domain.model.aggregates.User;
import com.identity.identity_service.iam.domain.model.entities.Role;
import com.identity.identity_service.iam.interfaces.rest.resources.AuthenticatedUserResource;

import java.util.stream.Collectors;

public class UserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token){
        return new AuthenticatedUserResource(
                user.getId(),
                user.getEmail(),
                user.getEmployee().getId().toString(),
                token,
                user.getRoles().stream().map(Role::getName).collect(Collectors.toList())
        );
    }
}
