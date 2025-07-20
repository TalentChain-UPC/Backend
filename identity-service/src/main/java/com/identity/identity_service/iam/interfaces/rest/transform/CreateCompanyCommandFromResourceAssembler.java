package com.identity.identity_service.iam.interfaces.rest.transform;

import com.identity.identity_service.iam.domain.model.commands.CreateCompanyCommand;

public class CreateCompanyCommandFromResourceAssembler {
    public static CreateCompanyCommand toCommandFromResource(
            String name,
            String ruc,
            String sector
    ){
        return new CreateCompanyCommand(name, ruc, sector);
    }
}
