package com.company.company_service.companies.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record CreateCompanyPlanCommand(
        @NotBlank Integer Plans_id,
        @NotBlank Integer Companies_id,
        @NotBlank Date start_date,
        @NotBlank Date end_date,
        @NotBlank String status
) {
}