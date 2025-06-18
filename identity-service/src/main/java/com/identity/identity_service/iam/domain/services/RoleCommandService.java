package com.identity.identity_service.iam.domain.services;

import com.identity.identity_service.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
