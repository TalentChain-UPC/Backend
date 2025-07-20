package com.rewards.rewards_service.rewards.domain.services;

import com.rewards.rewards_service.rewards.domain.model.commands.CreateCatalogCommand;
import com.rewards.rewards_service.rewards.domain.model.commands.DeleteCatalogCommand;
import com.rewards.rewards_service.rewards.domain.model.entities.Catalog;

import java.util.Optional;

public interface CatalogCommandService {
    Optional<Catalog> handle(CreateCatalogCommand command);
    Optional<Catalog> handle(DeleteCatalogCommand command);
}
