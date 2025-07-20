package com.rewards.rewards_service.rewards.interfaces.rest;

import com.rewards.rewards_service.rewards.domain.services.CatalogCommandService;
import com.rewards.rewards_service.rewards.interfaces.rest.resources.CreateCatalogResource;
import com.rewards.rewards_service.rewards.interfaces.rest.transform.CreateCatalogCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "/api/v1/catalog", produces = MediaType.APPLICATION_JSON_VALUE)
public class CatalogController {
    private final CatalogCommandService catalogCommandService;

    public CatalogController(CatalogCommandService catalogCommandService) {
        this.catalogCommandService = catalogCommandService;
    }
    @PostMapping
    public ResponseEntity<Long> createCatalogByCompanyId(@RequestBody CreateCatalogResource createCatalogResource) {
        var command = CreateCatalogCommandFromResourceAssembler.toCommandFromResource(createCatalogResource);
        var catalog = catalogCommandService.handle(command);
        if (catalog.isEmpty())return ResponseEntity.ok(0L);
        return new ResponseEntity<>(catalog.get().getId(), HttpStatus.CREATED);
    }
}
