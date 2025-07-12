package com.rewards.rewards_service.rewards.infrastructure.persistence.jpa.repositories;

import com.rewards.rewards_service.rewards.domain.model.entities.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog,Long> {
}
