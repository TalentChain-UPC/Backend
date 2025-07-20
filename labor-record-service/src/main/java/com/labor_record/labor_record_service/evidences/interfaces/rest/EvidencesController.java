package com.labor_record.labor_record_service.evidences.interfaces.rest;

import com.labor_record.labor_record_service.evidences.domain.model.queries.GetEvidencesByCompanyIdQuery;
import com.labor_record.labor_record_service.evidences.domain.services.EvidenceCommandService;
import com.labor_record.labor_record_service.evidences.domain.services.EvidenceQueryService;
import com.labor_record.labor_record_service.evidences.interfaces.rest.resources.CreateEvidenceResource;
import com.labor_record.labor_record_service.evidences.interfaces.rest.resources.EvidenceResource;
import com.labor_record.labor_record_service.evidences.interfaces.rest.resources.ValidateEvidenceResource;
import com.labor_record.labor_record_service.evidences.interfaces.rest.transform.CreateEvidenceCommandFromResourceAssembler;
import com.labor_record.labor_record_service.evidences.interfaces.rest.transform.EvidenceResourceFromEntityAssembler;
import com.labor_record.labor_record_service.evidences.interfaces.rest.transform.ValidateEvidenceCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/evidences", produces = MediaType.APPLICATION_JSON_VALUE)
public class EvidencesController {
    private final EvidenceCommandService evidenceCommandService;
    private final EvidenceQueryService evidenceQueryService;

    public EvidencesController(
            EvidenceCommandService evidenceCommandService,
            EvidenceQueryService evidenceQueryService) {
        this.evidenceCommandService = evidenceCommandService;
        this.evidenceQueryService = evidenceQueryService;
    }

    @PostMapping
    public ResponseEntity<EvidenceResource> uploadEvidence(
            @RequestBody CreateEvidenceResource createEvidenceResource) {
        var command = CreateEvidenceCommandFromResourceAssembler.toCommandFromResource(createEvidenceResource);
        var evidence = evidenceCommandService.handle(command);
        if (evidence.isEmpty()) return ResponseEntity.badRequest().build();
        var resource = EvidenceResourceFromEntityAssembler.toResourceFromEntity(evidence.get());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EvidenceResource> validateEvidence(
            @PathVariable Long id,
            @RequestBody ValidateEvidenceResource validateEvidenceResource) {
        var command = ValidateEvidenceCommandFromResourceAssembler
                .toCommandFromResource(validateEvidenceResource,id);
        var evidence = evidenceCommandService.handle(command);
        if (evidence.isEmpty()) return ResponseEntity.badRequest().build();
        var resource = EvidenceResourceFromEntityAssembler.toResourceFromEntity(evidence.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<List<EvidenceResource>> getEvidenceByCompanyId(@PathVariable Long id) {
        var evidences = evidenceQueryService.getEvidencesByCompanyId(new GetEvidencesByCompanyIdQuery(id));
        var resources = evidences.stream()
                .map(EvidenceResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }
}
