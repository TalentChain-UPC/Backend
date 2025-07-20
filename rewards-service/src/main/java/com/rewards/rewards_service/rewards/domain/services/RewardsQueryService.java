package com.rewards.rewards_service.rewards.domain.services;

import com.rewards.rewards_service.rewards.domain.model.commands.CreateRewardCommand;
import com.rewards.rewards_service.rewards.domain.model.entities.Rewards;
import com.rewards.rewards_service.rewards.domain.model.queries.GetAllRewardsQuery;
import com.rewards.rewards_service.rewards.domain.model.queries.GetRewardByIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RewardsQueryService {
    Optional<Rewards> handle(GetRewardByIdQuery query);
    List<Rewards> handle(GetAllRewardsQuery query);
}
