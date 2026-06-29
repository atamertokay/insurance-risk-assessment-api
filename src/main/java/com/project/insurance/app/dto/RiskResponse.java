package com.project.insurance.app.dto;

import com.project.insurance.app.model.RiskLevel;

public class RiskResponse {

    private int riskScore;
    private RiskLevel riskLevel;

    public RiskResponse(int riskScore, RiskLevel riskLevel) {
        this.riskScore = riskScore;
        this.riskLevel = riskLevel;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }
}
