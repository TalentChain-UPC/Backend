package com.rewards.rewards_service.rewards.domain.services;

import com.rewards.rewards_service.rewards.domain.model.commands.CreateRewardCommand;
import com.rewards.rewards_service.rewards.domain.model.commands.DeleteRewardCommand;
import com.rewards.rewards_service.rewards.domain.model.entities.Rewards;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RewardsCommandService {
    Optional<Rewards> handle(CreateRewardCommand command);
    Optional<Rewards> handle(DeleteRewardCommand command);
}
