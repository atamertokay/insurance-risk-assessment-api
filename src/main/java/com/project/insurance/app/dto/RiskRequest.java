package com.project.insurance.app.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RiskRequest {

    @NotNull(message = "Yaş zorunludur")
    @Min(value = 18, message = "Yaş en az 18 olmalıdır")
    @Max(value = 120, message = "Yaş en fazla 120 olabilir")
    private Integer age;

    @NotNull(message = "Sigara bilgisi zorunludur")
    private Boolean smoker;

    @NotNull(message = "BMI zorunludur")
    @Positive(message = "BMI 0'dan büyük olmalıdır")
    private Double bmi;

    @NotNull(message = "Gelir zorunludur")
    @PositiveOrZero(message = "Gelir negatif olamaz")
    private Double income;

    @NotNull(message = "Kronik hastalık bilgisi zorunludur")
    private Boolean chronicDisease;



}