package com.rewards.rewards_service.rewards.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateRewardCommand(
        @NotBlank Integer Catalog_id,
        @NotBlank String name,
        @NotBlank Integer price,
        @NotBlank String description,
        @NotBlank String image,
        @NotBlank String type
) {
}
