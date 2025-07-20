package com.rewards.rewards_service.rewards.interfaces.rest.transform;

import com.rewards.rewards_service.rewards.domain.model.entities.Rewards;
import com.rewards.rewards_service.rewards.interfaces.rest.resources.RewardsResource;

public class RewardsResourceFromEntityAssembler {
    public static RewardsResource transformResourceFromEntity(Rewards rewards){
        return new RewardsResource(
                rewards.getId(),
                rewards.getCatalog_id(),
                rewards.getName(),
                rewards.getPrice(),
                rewards.getDescription(),
                rewards.getImage(),
                rewards.getType()
        );
    }
}
