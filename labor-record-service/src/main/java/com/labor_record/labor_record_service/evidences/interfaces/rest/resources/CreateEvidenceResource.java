package com.labor_record.labor_record_service.evidences.interfaces.rest.resources;

public record CreateEvidenceResource(
        String type,
        String description,
        Long employeeId,
        Long companyId,
        String data,
        String url,
        String name,
        String institutionName,
        String issuedDate
) {
    public CreateEvidenceResource(
            String type,
            String description,
            Long employeeId,
            Long companyId,
            String data,
            String url,
            String name,
            String institutionName,
            String issuedDate
    ) {
        this.type = type;
        this.description = description;
        this.employeeId = employeeId;
        this.companyId = companyId;
        this.data = data;
        this.url = (url != null) ? url : "";
        this.name = (name != null) ? name : "";
        this.institutionName = (institutionName != null) ? institutionName : "";
        this.issuedDate = (issuedDate != null) ? issuedDate : "";
    }
}
