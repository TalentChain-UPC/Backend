package com.rewards.rewards_service.rewards.interfaces.rest;

import com.rewards.rewards_service.rewards.domain.model.commands.DeleteRewardCommand;
import com.rewards.rewards_service.rewards.domain.model.queries.GetAllRewardsQuery;
import com.rewards.rewards_service.rewards.domain.model.queries.GetRewardByIdQuery;
import com.rewards.rewards_service.rewards.domain.services.RewardsCommandService;
import com.rewards.rewards_service.rewards.domain.services.RewardsQueryService;
import com.rewards.rewards_service.rewards.interfaces.rest.resources.CreateRewardsResource;
import com.rewards.rewards_service.rewards.interfaces.rest.resources.RewardsResource;
import com.rewards.rewards_service.rewards.interfaces.rest.transform.CreateRewardsCommandFromResourceAssembler;
import com.rewards.rewards_service.rewards.interfaces.rest.transform.RewardsResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/rewards", produces = MediaType.APPLICATION_JSON_VALUE)
public class RewardsController {
    private final RewardsCommandService rewardsCommandService;
    private final RewardsQueryService rewardsQueryService;
    public RewardsController(RewardsCommandService rewardsCommandService, RewardsQueryService rewardsQueryService) {
        this.rewardsCommandService = rewardsCommandService;
        this.rewardsQueryService = rewardsQueryService;
    }
    @GetMapping
    public ResponseEntity<List<RewardsResource>> getAllRewards() {
        var rewards = rewardsQueryService.handle(new GetAllRewardsQuery());
        var rewardsResource = rewards.stream().map(RewardsResourceFromEntityAssembler::transformResourceFromEntity).toList();
        return ResponseEntity.ok(rewardsResource);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RewardsResource> getRewardsById(@PathVariable Long id) {
        var rewards = rewardsQueryService.handle(new GetRewardByIdQuery(id));
        if (rewards.isEmpty()) {
            throw new IllegalArgumentException("Reward with id " + id + " not found");
        }
        var rewardsResource = RewardsResourceFromEntityAssembler.transformResourceFromEntity(rewards.get());
        return ResponseEntity.ok(rewardsResource);
    }
    @PostMapping
    public ResponseEntity<RewardsResource> createRewards(@RequestBody CreateRewardsResource resource) {
        var createRewardsCommand = CreateRewardsCommandFromResourceAssembler.toCommandFromResource(resource);
        var rewards = rewardsCommandService.handle(createRewardsCommand);
        if (rewards.isEmpty()) return ResponseEntity.badRequest().build();
        var rewardsResource = RewardsResourceFromEntityAssembler.transformResourceFromEntity(rewards.get());
        return new ResponseEntity<RewardsResource>(rewardsResource, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RewardsResource> deleteRewards(@PathVariable Long id) {
        var deleteRewardsCommand = new DeleteRewardCommand(id);
        var rewards = rewardsCommandService.handle(deleteRewardsCommand);
        if (rewards.isEmpty()) return ResponseEntity.badRequest().build();
        var rewardsResource = RewardsResourceFromEntityAssembler.transformResourceFromEntity(rewards.get());
        return ResponseEntity.ok(rewardsResource);
    }
}
