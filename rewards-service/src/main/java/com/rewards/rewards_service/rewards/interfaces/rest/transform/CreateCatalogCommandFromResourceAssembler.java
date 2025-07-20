package com.rewards.rewards_service.rewards.interfaces.rest.transform;

import com.rewards.rewards_service.rewards.domain.model.commands.CreateCatalogCommand;
import com.rewards.rewards_service.rewards.interfaces.rest.resources.CreateCatalogResource;

public class CreateCatalogCommandFromResourceAssembler {
    public static CreateCatalogCommand toCommandFromResource(CreateCatalogResource resource) {
        return new CreateCatalogCommand(
                resource.Companies_id()
        );
    }
}
