package com.rewards.rewards_service.rewards.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateCatalogCommand(
        @NotBlank Long Companies_id
) {
}
