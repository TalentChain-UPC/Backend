package com.rewards.rewards_service.rewards.application.internal;

import com.rewards.rewards_service.rewards.domain.model.entities.Rewards;
import com.rewards.rewards_service.rewards.domain.model.queries.GetAllRewardsQuery;
import com.rewards.rewards_service.rewards.domain.model.queries.GetRewardByIdQuery;
import com.rewards.rewards_service.rewards.domain.services.RewardsQueryService;
import com.rewards.rewards_service.rewards.infrastructure.persistence.jpa.repositories.RewardsRepository;

import java.util.List;
import java.util.Optional;

public class RewardsQueryServiceImpl implements RewardsQueryService {
    private final RewardsRepository rewardsRepository;
    public RewardsQueryServiceImpl(final RewardsRepository rewardsRepository) {
        this.rewardsRepository = rewardsRepository;
    }
    @Override
    public Optional<Rewards> handle(GetRewardByIdQuery query) { return rewardsRepository.findById(query.id()); }
    @Override
    public List<Rewards> handle(GetAllRewardsQuery query) { return rewardsRepository.findAll(); }
}
