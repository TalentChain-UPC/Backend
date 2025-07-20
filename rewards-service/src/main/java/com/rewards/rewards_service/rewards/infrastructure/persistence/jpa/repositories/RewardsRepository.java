package com.rewards.rewards_service.rewards.infrastructure.persistence.jpa.repositories;

import com.rewards.rewards_service.rewards.domain.model.entities.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardsRepository extends JpaRepository<Rewards, Long> {
}
