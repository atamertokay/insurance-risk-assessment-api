package com.project.insurance.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RiskRequest {
    @Schema(
            description = "Age of the applicant",
            example = "25"
    )
    @NotNull(message = "Age is mandatory")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age can be at most 120")
    private Integer age;

    @Schema(
            description = "Applicant's smoking status",
            example = "false"
    )
    @NotNull(message = "Smoking status is mandatory")
    private Boolean smoker;

    @Schema(
            description = "Applicant's body mass index",
            example = "22.5"
    )
    @NotNull(message = "BMI is mandatory.")
    @Positive(message = "BMI must be greater than 0.")
    private Double bmi;

    @Schema(
            description = "Income of the applicant",
            example = "20000"
    )
    @NotNull(message = "Income is mandatory")
    @PositiveOrZero(message = "Income can't be negative")
    private Double income;

    @Schema(
            description = "Applicant's chronic disease status",
            example = "true"
    )
    @NotNull(message = "Chronic disease status is mandatory")
    private Boolean chronicDisease;



}