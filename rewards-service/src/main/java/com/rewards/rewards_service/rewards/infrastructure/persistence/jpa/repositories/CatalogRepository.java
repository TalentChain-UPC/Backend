package com.rewards.rewards_service.rewards.infrastructure.persistence.jpa.repositories;

import com.rewards.rewards_service.rewards.domain.model.entities.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Long> {
    boolean existsByCompanies_id(Long companiesId);
}
