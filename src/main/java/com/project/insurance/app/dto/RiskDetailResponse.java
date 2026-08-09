package com.project.insurance.app.dto;

import lombok.Getter;

@Getter
public class RiskDetailResponse {
    private Long id;
    private Integer age;
    private Boolean smoker;
    private Double bmi;
    private Double income;
    private Boolean chronicDisease;

    public RiskDetailResponse(Long id, Integer age, Boolean smoker, Double bmi, Double income, Boolean chronicDisease) {
        this.id = id;
        this.age = age;
        this.smoker = smoker;
        this.bmi = bmi;
        this.income = income;
        this.chronicDisease = chronicDisease;
    }


}
