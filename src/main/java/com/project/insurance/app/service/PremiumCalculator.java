package com.project.insurance.app.service;

import org.springframework.stereotype.Component;

@Component
public class PremiumCalculator {

    private static final double BASE_PREMIUM = 1000.0;

    private static final double LOW_RISK_INCREMENT = 500.0;
    private static final double MEDIUM_RISK_INCREMENT = 1000.0;
    private static final double HIGH_RISK_INCREMENT = 2000.0;
    private static final double VERY_HIGH_RISK_INCREMENT = 3500.0;

    public Double calculatePremium(int score) {

        if (score < 0 || score > 100) {
            throw new IllegalArgumentException(
                    "Risk score must be between 0 and 100.");
        }

        if (score <= 20) {
            return BASE_PREMIUM;
        }

        if (score <= 40) {
            return BASE_PREMIUM + LOW_RISK_INCREMENT;
        }

        if (score <= 60) {
            return BASE_PREMIUM + MEDIUM_RISK_INCREMENT;
        }

        if (score <= 80) {
            return BASE_PREMIUM + HIGH_RISK_INCREMENT;
        }

        return BASE_PREMIUM + VERY_HIGH_RISK_INCREMENT;
    }
}
