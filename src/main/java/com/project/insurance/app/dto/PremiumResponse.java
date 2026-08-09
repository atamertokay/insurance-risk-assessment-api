package com.project.insurance.app.dto;

import com.project.insurance.app.model.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PremiumResponse {
    private Integer riskScore;

    private RiskLevel riskLevel;

    private Double monthlyPremium;




}
