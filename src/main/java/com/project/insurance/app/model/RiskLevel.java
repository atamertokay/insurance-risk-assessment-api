package com.project.insurance.app.model;

public enum RiskLevel {

    BASE,
    LOW,
    MEDIUM,
    HIGH,
    VERY_HIGH;

    public static RiskLevel fromScore(int score) {

        if (score < 0 || score > 100) {
            throw new IllegalArgumentException(
                    "Risk score must be between 0 and 100"
            );
        }

        if (score > 80) return VERY_HIGH;
        if (score > 60) return HIGH;
        if (score > 40) return MEDIUM;
        if (score > 20) return LOW;
        return BASE;
    }
}