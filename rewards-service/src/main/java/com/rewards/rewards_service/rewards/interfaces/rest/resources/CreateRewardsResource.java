package com.rewards.rewards_service.rewards.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record CreateRewardsResource(
        @NotBlank Integer Catalog_id,
        @NotBlank String name,
        @NotBlank Integer price,
        @NotBlank String description,
        @NotBlank String image,
        @NotBlank String type
) {
}
