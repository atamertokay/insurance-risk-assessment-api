package com.project.insurance.app.service;

import com.project.insurance.app.model.RiskLevel;
import org.springframework.stereotype.Component;

@Component
public class PremiumCalculator {

    private static final double BASE_PREMIUM = 1000.0;
    private static final double LOW_RISK_INCREMENT = 500.0;
    private static final double MEDIUM_RISK_INCREMENT = 1000.0;
    private static final double HIGH_RISK_INCREMENT = 2000.0;
    private static final double VERY_HIGH_RISK_INCREMENT = 3500.0;

    public double calculatePremium(RiskLevel riskLevel) {

        if (riskLevel == null) {
            throw new IllegalArgumentException(
                    "Risk level cannot be null"
            );
        }

        return switch (riskLevel) {
            case BASE -> BASE_PREMIUM;
            case LOW -> BASE_PREMIUM + LOW_RISK_INCREMENT;
            case MEDIUM -> BASE_PREMIUM + MEDIUM_RISK_INCREMENT;
            case HIGH -> BASE_PREMIUM + HIGH_RISK_INCREMENT;
            case VERY_HIGH ->
                    BASE_PREMIUM + VERY_HIGH_RISK_INCREMENT;
        };
    }
}
