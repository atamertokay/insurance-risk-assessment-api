package com.project.insurance.app.service;

import com.project.insurance.app.model.RiskLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PremiumCalculatorTest {
    private final PremiumCalculator premiumCalculator =
            new PremiumCalculator();


    @Test
    void shouldReturnBasePremiumForBaseRisk() {
        double premium =
                premiumCalculator.calculatePremium(RiskLevel.BASE);

        assertEquals(1000.0, premium);
    }

    @Test
    void shouldReturnLowRiskPremium() {
        assertEquals(
                1500.0,
                premiumCalculator.calculatePremium(RiskLevel.LOW)
        );
    }

    @Test
    void shouldReturnMediumRiskPremium() {
        assertEquals(
                2000.0,
                premiumCalculator.calculatePremium(RiskLevel.MEDIUM)
        );
    }

    @Test
    void shouldReturnHighRiskPremium() {
        assertEquals(
                3000.0,
                premiumCalculator.calculatePremium(RiskLevel.HIGH)
        );
    }

    @Test
    void shouldReturnVeryHighRiskPremium() {
        double premium =
                premiumCalculator.calculatePremium(RiskLevel.VERY_HIGH);

        assertEquals(4500.0, premium);
    }

    @Test
    void shouldThrowExceptionWhenRiskLevelIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> premiumCalculator.calculatePremium(null)
        );
    }


}
