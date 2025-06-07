package com.identity.identity_service.iam.interfaces.rest.transform;

import com.identity.identity_service.iam.domain.model.aggregates.User;
import com.identity.identity_service.iam.domain.model.entities.Role;
import com.identity.identity_service.iam.interfaces.rest.resources.UserResource;

import java.util.stream.Collectors;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity){
        Long employeeId=0L;
        if (entity.getEmployee() == null){
            employeeId=0L;
        }else {
            employeeId = entity.getEmployee().getId();
        }
        return new UserResource(
                entity.getId(),
                entity.getEmail(),
                employeeId,
                entity.getActive(),
                entity.getRoles().stream().map(Role::getName).collect(Collectors.toList())
        );
    }
}
