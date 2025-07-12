package com.rewards.rewards_service.rewards.application.internal;

import com.rewards.rewards_service.rewards.domain.model.commands.CreateRewardCommand;
import com.rewards.rewards_service.rewards.domain.model.commands.DeleteRewardCommand;
import com.rewards.rewards_service.rewards.domain.model.entities.Rewards;
import com.rewards.rewards_service.rewards.domain.services.RewardsCommandService;
import com.rewards.rewards_service.rewards.infrastructure.persistence.jpa.repositories.RewardsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RewardsCommandServiceImpl implements RewardsCommandService {
    private final RewardsRepository rewardsRepository;
    public RewardsCommandServiceImpl(RewardsRepository rewardsRepository) {
        this.rewardsRepository = rewardsRepository;
    }
    @Override
    public Optional<Rewards> handle(CreateRewardCommand command) {
        var rewards = new Rewards(command);
        rewardsRepository.save(rewards);
        return Optional.of(rewards);
    }
    @Override
    public Optional<Rewards> handle(DeleteRewardCommand command) {
        var rewards = rewardsRepository.findById(command.id());
        if (rewards.isEmpty()) {
            throw new IllegalArgumentException("Rewards with id " + command.id() + " not found");
        }
        rewardsRepository.delete(rewards.get());
        return rewards;
    }
}
