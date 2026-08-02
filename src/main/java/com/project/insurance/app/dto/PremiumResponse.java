package com.project.insurance.app.dto;

import com.project.insurance.app.model.RiskLevel;
import lombok.Data;

@Data
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


}
