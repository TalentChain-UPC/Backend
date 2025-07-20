package com.rewards.rewards_service.rewards.application.internal;

import com.rewards.rewards_service.rewards.domain.model.commands.CreateCatalogCommand;
import com.rewards.rewards_service.rewards.domain.model.commands.DeleteCatalogCommand;
import com.rewards.rewards_service.rewards.domain.model.entities.Catalog;
import com.rewards.rewards_service.rewards.domain.services.CatalogCommandService;
import com.rewards.rewards_service.rewards.infrastructure.persistence.jpa.repositories.CatalogRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatalogCommandServiceImpl implements CatalogCommandService {

    private final CatalogRepository catalogRepository;

    public CatalogCommandServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Optional<Catalog> handle(CreateCatalogCommand command) {
        if (catalogRepository.existsByCompanies_id(command.Companies_id())){
            return Optional.empty();
        }
        var catalog = new Catalog(command);
        catalogRepository.save(catalog);
        return Optional.of(catalog);
    }

    @Override
    public Optional<Catalog> handle(DeleteCatalogCommand command) {
        return Optional.empty();
    }
}
