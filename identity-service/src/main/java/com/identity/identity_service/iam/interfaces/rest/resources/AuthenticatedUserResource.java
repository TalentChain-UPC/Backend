package com.identity.identity_service.iam.interfaces.rest.resources;

import com.identity.identity_service.iam.domain.model.valueObjects.Roles;

import java.util.List;

public record AuthenticatedUserResource(
        Long id,
        String username,
        Long employeeId,
        Long companyId,
        String token,
        List<Roles> roles
) {
}
