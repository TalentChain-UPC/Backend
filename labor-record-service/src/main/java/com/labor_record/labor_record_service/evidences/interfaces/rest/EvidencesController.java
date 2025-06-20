package com.labor_record.labor_record_service.evidences.interfaces.rest;

import com.labor_record.labor_record_service.evidences.domain.services.EvidenceCommandService;
import com.labor_record.labor_record_service.evidences.interfaces.rest.resources.CreateEvidenceResource;
import com.labor_record.labor_record_service.evidences.interfaces.rest.resources.EvidenceResource;
import com.labor_record.labor_record_service.evidences.interfaces.rest.transform.CreateEvidenceCommandFromResourceAssembler;
import com.labor_record.labor_record_service.evidences.interfaces.rest.transform.EvidenceResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "/api/v1/evidences", produces = MediaType.APPLICATION_JSON_VALUE)
public class EvidencesController {
    private final EvidenceCommandService evidenceCommandService;

    public EvidencesController(EvidenceCommandService evidenceCommandService) {
        this.evidenceCommandService = evidenceCommandService;
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
}
