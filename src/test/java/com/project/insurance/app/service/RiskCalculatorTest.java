package com.project.insurance.app.service;

import com.project.insurance.app.dto.RiskRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RiskCalculatorTest {
    private final RiskCalculator riskCalculator = new RiskCalculator();
    @Test
    void shouldCalculateLowRiskScore() {


        RiskRequest riskRequest = new RiskRequest();

        riskRequest.setAge(25);
        riskRequest.setSmoker(false);
        riskRequest.setBmi(22.0);
        riskRequest.setIncome(100000.0);
        riskRequest.setChronicDisease(false);


        int actualScore = riskCalculator.calculateScore(
                riskRequest.getAge(),
                riskRequest.getSmoker(),
                riskRequest.getBmi(),
                riskRequest.getChronicDisease(),
                riskRequest.getIncome()
        );


        assertEquals(0 , actualScore);
    }
}
