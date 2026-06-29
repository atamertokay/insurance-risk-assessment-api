package com.project.insurance.app.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class RiskRequest {

    @Min(18)
    @Max(120)
    private Integer age;

    private Boolean smoker;

    @Positive
    private Double bmi;

    @PositiveOrZero
    private Double income;

    private Boolean chronicDisease;



}