package com.project.insurance.app.dto;

import com.project.insurance.app.model.RiskLevel;

public class PremiumResponse {
    private Integer riskScore;

    private RiskLevel riskLevel;

    private Double monthlyPremium;

    public PremiumResponse(
            Integer riskScore,
            RiskLevel riskLevel,
            Double monthlyPremium) {

        this.riskScore = riskScore;
        this.riskLevel = riskLevel;
        this.monthlyPremium = monthlyPremium;
    }

    public Double getMonthlyPremium() {
        return monthlyPremium;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public Integer getRiskScore() {
        return riskScore;
    }
}
