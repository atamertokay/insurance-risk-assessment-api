package com.project.insurance.app.dto;

import com.project.insurance.app.model.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RiskResponse {

    private int riskScore;
    private RiskLevel riskLevel;


}
