package com.project.insurance.app.dto;

import lombok.Getter;

public class RiskSummaryResponse {

    private Long id;
    private Integer age;
    private Double bmi;

    public RiskSummaryResponse(
            Long id,
            Integer age,
            Double bmi) {

        this.id = id;
        this.age = age;
        this.bmi = bmi;
    }

    public Long getId() {
        return id;
    }

    public Double getBmi() {
        return bmi;
    }

    public Integer getAge() {
        return age;
    }
}
