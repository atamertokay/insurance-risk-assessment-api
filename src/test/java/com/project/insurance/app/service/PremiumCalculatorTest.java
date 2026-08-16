package com.project.insurance.app.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PremiumCalculatorTest {
    private final PremiumCalculator premiumCalculator =
            new PremiumCalculator();

    @Test
    void shouldReturnBasePremiumWhenScoreIs20OrLess() {

        Double premium =
                premiumCalculator.calculatePremium(0);

        assertEquals(1000.0, premium);
    }
    @Test
    void shouldReturnBasePremiumWhenScoreIs20() {

        // Arrange
        int score = 20;

        // Act
        Double premium =
                premiumCalculator.calculatePremium(score);

        // Assert
        assertEquals(1000.0, premium);
    }
    @Test
    void shouldReturn1500WhenScoreIs21() {

        // Arrange
        int score = 21;

        // Act
        Double premium =
                premiumCalculator.calculatePremium(score);

        // Assert
        assertEquals(1500.0, premium);
    }
    @Test
    void shouldReturn1500WhenScoreIs40() {

        Double premium =
                premiumCalculator.calculatePremium(40);

        assertEquals(1500.0, premium);
    }
    @Test
    void shouldReturn2000WhenScoreIs41() {

        Double premium =
                premiumCalculator.calculatePremium(41);

        assertEquals(2000.0, premium);
    }
    @Test
    void shouldReturn2000WhenScoreIs60() {

        Double premium =
                premiumCalculator.calculatePremium(60);

        assertEquals(2000.0, premium);
    }
    @Test
    void shouldReturn30000WhenScoreIs61() {

        Double premium =
                premiumCalculator.calculatePremium(61);

        assertEquals(3000.0, premium);
    }
    @Test
    void shouldReturn3000WhenScoreIs80() {

        Double premium =
                premiumCalculator.calculatePremium(80);

        assertEquals(3000.0, premium);
    }
    @Test
    void shouldReturn4500WhenScoreIs81() {

        Double premium =
                premiumCalculator.calculatePremium(81);

        assertEquals(4500.0, premium);
    }
    @Test
    void shouldReturn4500WhenScoreIs100() {

        Double premium =
                premiumCalculator.calculatePremium(100);

        assertEquals(4500.0, premium);
    }
    @Test
    void shouldThrowExceptionWhenScoreIsNegative() {

        // Arrange
        int score = -1;

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> premiumCalculator.calculatePremium(score)
        );
    }
    @Test
    void shouldThrowExceptionWhenScoreIs101() {

        // Arrange
        int score = 101;

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> premiumCalculator.calculatePremium(score)
        );
    }
}
