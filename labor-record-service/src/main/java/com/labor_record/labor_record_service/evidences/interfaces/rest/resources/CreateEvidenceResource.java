package com.labor_record.labor_record_service.evidences.interfaces.rest.resources;

public record CreateEvidenceResource(
        String type,
        String description,
        Long employeeId,
        String url
) {
    public CreateEvidenceResource (
            String type,
            String description,
            Long employeeId,
            String url
    ){
        this.type = type;
        this.description = description;
        this.employeeId = (employeeId != null) ? employeeId : 0L;
        this.url = (url != null) ? url : "";
    }
}
