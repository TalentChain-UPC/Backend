package com.identity.identity_service.iam.domain.model.commands;

import com.identity.identity_service.clients.domain.model.aggregates.Employee;

public record CreateManagerCommand(
        String email,
        String password,
        Boolean isActive,
        Employee employee,
        String name,
        String lastName
) {
}
