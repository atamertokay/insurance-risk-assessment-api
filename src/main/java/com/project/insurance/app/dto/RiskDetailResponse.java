package com.project.insurance.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RiskDetailResponse {
    private Long id;
    private Integer age;
    private Boolean smoker;
    private Double bmi;
    private Double income;
    private Boolean chronicDisease;


}
