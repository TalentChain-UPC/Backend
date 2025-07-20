package com.rewards.rewards_service.rewards.interfaces.rest.transform;

import com.rewards.rewards_service.rewards.domain.model.entities.Catalog;
import com.rewards.rewards_service.rewards.interfaces.rest.resources.CatalogResource;

public class CatalogResourceFromEntityAssembler {
    public static CatalogResource transformResourceFromEntity(Catalog catalog) {
        return new CatalogResource(
                catalog.getId(),
                catalog.getCompanies_id()
        );
    }
}
