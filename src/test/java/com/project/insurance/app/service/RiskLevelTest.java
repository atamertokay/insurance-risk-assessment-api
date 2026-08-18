package com.project.insurance.app.service;

import com.project.insurance.app.model.RiskLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RiskLevelTest {


    @Test
    void shouldReturnBaseAtScore0() {
        assertEquals(RiskLevel.BASE, RiskLevel.fromScore(0));
    }
    @Test
    void shouldReturnBaseAtScore20() {
        assertEquals(RiskLevel.BASE, RiskLevel.fromScore(20));
    }

    @Test
    void shouldReturnLowAtScore21() {
        assertEquals(RiskLevel.LOW, RiskLevel.fromScore(21));
    }
    @Test
    void shouldReturnLowAtScore40() {
        assertEquals(RiskLevel.LOW, RiskLevel.fromScore(40));
    }
    @Test
    void shouldReturnMediumAtScore41() {
        assertEquals(RiskLevel.MEDIUM, RiskLevel.fromScore(41));
    }
    @Test
    void shouldReturnMediumAtScore60() {
        assertEquals(RiskLevel.MEDIUM, RiskLevel.fromScore(60));
    }
    @Test
    void shouldReturnHighAtScore61() {
        assertEquals(RiskLevel.HIGH, RiskLevel.fromScore(61));
    }
    @Test
    void shouldReturnHighAtScore80() {
        assertEquals(RiskLevel.HIGH, RiskLevel.fromScore(80));
    }

    @Test
    void shouldReturnVeryHighAtScore81() {
        assertEquals(RiskLevel.VERY_HIGH, RiskLevel.fromScore(81));
    }

    @Test
    void shouldRejectScoreAbove100() {
        assertThrows(
                IllegalArgumentException.class,
                () -> RiskLevel.fromScore(101)
        );
    }
    @Test
    void shouldRejectScoreBelow0() {
        assertThrows(
                IllegalArgumentException.class,
                () -> RiskLevel.fromScore(-1));
    }
}
