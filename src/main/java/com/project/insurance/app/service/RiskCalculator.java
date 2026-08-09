package com.project.insurance.app.service;


import org.springframework.stereotype.Component;

@Component
public class RiskCalculator {

    public int calculateScore(
            Integer age,
            Boolean smoker,
            Double bmi,
            Boolean chronicDisease,
            Double income) {

        int score = calculateAgeRisk(age)
                + calculateSmokingRisk(smoker)
                + calculateBmiRisk(bmi)
                + calculateDiseaseRisk(chronicDisease)
                + calculateIncomeRisk(income);

        return Math.max(0, Math.min(score, 100));
    }

    private int calculateAgeRisk(Integer age) {

        if (age < 30) {
            return 5;
        }

        if (age < 50) {
            return 15;
        }

        return 25;
    }

    private int calculateSmokingRisk(Boolean smoker) {

        if(smoker == true){
            return 25;
        }

        return 0;

    }

    private int calculateBmiRisk(Double bmi) {

        if (bmi < 25) {
            return 0;
        }

        if (bmi < 30) {
            return 10;
        }

        return 20;
    }

    private int calculateDiseaseRisk(Boolean chronicDisease) {
       if (chronicDisease  == true){
           return 25;
       }
       return 0;
    }

    private int calculateIncomeRisk(Double income) {

        if (income >= 100000) {
            return -10;
        }

        if (income >= 50000) {
            return 0;
        }

        return 5;
    }

}

