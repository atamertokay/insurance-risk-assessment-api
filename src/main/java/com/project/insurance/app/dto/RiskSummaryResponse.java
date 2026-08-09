package com.project.insurance.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RiskSummaryResponse {

    private Long id;
    private Integer age;
    private Double bmi;


}
