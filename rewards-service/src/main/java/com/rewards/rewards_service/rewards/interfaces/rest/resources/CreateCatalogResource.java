package com.rewards.rewards_service.rewards.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record CreateCatalogResource(
        @NotBlank Long Companies_id
) {
}
