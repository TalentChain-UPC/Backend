package com.rewards.rewards_service.rewards.interfaces.rest.resources;

public record RewardsResource(
        Long id,
        Integer Catalog_id,
        String name,
        Integer price,
        String description,
        String image,
        String type
) {
}
