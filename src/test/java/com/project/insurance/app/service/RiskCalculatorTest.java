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
    @Test
    void shouldCalculateMediumRiskScore() {


        RiskRequest riskRequest = new RiskRequest();

        riskRequest.setAge(45);
        riskRequest.setSmoker(true);
        riskRequest.setBmi(27.0);
        riskRequest.setIncome(20000.0);
        riskRequest.setChronicDisease(false);


        int actualScore = riskCalculator.calculateScore(
                riskRequest.getAge(),
                riskRequest.getSmoker(),
                riskRequest.getBmi(),
                riskRequest.getChronicDisease(),
                riskRequest.getIncome()
        );


        assertEquals(55 , actualScore);
    }
    @Test
    void shouldCalculateHighRiskScore() {


        RiskRequest riskRequest = new RiskRequest();

        riskRequest.setAge(75);
        riskRequest.setSmoker(true);
        riskRequest.setBmi(22.0);
        riskRequest.setIncome(40000.0);
        riskRequest.setChronicDisease(true);


        int actualScore = riskCalculator.calculateScore(
                riskRequest.getAge(),
                riskRequest.getSmoker(),
                riskRequest.getBmi(),
                riskRequest.getChronicDisease(),
                riskRequest.getIncome()
        );


        assertEquals(80 , actualScore);
    }
    @Test
    void shouldCalculateVeryHighRiskScore() {


        RiskRequest riskRequest = new RiskRequest();

        riskRequest.setAge(75);
        riskRequest.setSmoker(true);
        riskRequest.setBmi(42.0);
        riskRequest.setIncome(40000.0);
        riskRequest.setChronicDisease(true);


        int actualScore = riskCalculator.calculateScore(
                riskRequest.getAge(),
                riskRequest.getSmoker(),
                riskRequest.getBmi(),
                riskRequest.getChronicDisease(),
                riskRequest.getIncome()
        );


        assertEquals(100 , actualScore);
    }
}
