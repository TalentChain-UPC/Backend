package com.identity.identity_service.iam.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateBatchByCompanyResource(
        @NotBlank String name,
        @NotBlank String ruc,
        @NotBlank String sector,
        List<RegisterEmployeeResource> employees
) {
}
