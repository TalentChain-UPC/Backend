package com.identity.identity_service.iam.interfaces.rest.transform;

import com.identity.identity_service.iam.domain.model.commands.SignInCommand;
import com.identity.identity_service.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource){
        return new SignInCommand(resource.email(), resource.password());
    }
}
