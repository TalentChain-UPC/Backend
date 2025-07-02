package com.rewards.rewards_service.rewards.interfaces.rest.transform;

import com.rewards.rewards_service.rewards.domain.model.commands.CreateRewardCommand;
import com.rewards.rewards_service.rewards.interfaces.rest.resources.CreateRewardsResource;

public class CreateRewardsCommandFromResourceAssembler {
    public static CreateRewardCommand toCommandFromResource(CreateRewardsResource resource) {
        return new CreateRewardCommand(
                resource.Catalog_id(),
                resource.name(),
                resource.price(),
                resource.description(),
                resource.image(),
                resource.type()
        );
    }
}
