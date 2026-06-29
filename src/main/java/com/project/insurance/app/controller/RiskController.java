package com.project.insurance.app.controller;

import com.project.insurance.app.dto.PremiumResponse;
import com.project.insurance.app.dto.RiskRequest;
import com.project.insurance.app.dto.RiskResponse;
import com.project.insurance.app.dto.RiskSummaryResponse;
import com.project.insurance.app.entity.InsuranceRequirements;
import com.project.insurance.app.mapper.RiskMapper;
import com.project.insurance.app.service.RiskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/risk")
public class RiskController {

    private final RiskService riskService;



    public RiskController(RiskService riskService, RiskMapper mapper) {
        this.riskService = riskService;
    }

    @PostMapping
    public InsuranceRequirements create(@RequestBody RiskRequest request) {
        return riskService.create(request);
    }

    @GetMapping
    public List<RiskSummaryResponse> getAll() {
        return riskService.getAll();
    }

    @PostMapping("/calculate")
    public RiskResponse calculateRisk(
            @Valid @RequestBody RiskRequest request) {

        return riskService.calculateRisk(request);
    }
    @GetMapping("/{id}")
    public InsuranceRequirements getById(
            @PathVariable Long id) {

        return riskService.getById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(
            @PathVariable Long id) {

        riskService.deleteById(id);

        return ResponseEntity.ok(
                "Kayıt başarıyla silindi.");
    }
    @PutMapping("/{id}")
    public InsuranceRequirements update(
            @PathVariable Long id,
            @Valid @RequestBody RiskRequest request) {

        return riskService.update(id, request);
    }
    @GetMapping("/high")
    public List<RiskSummaryResponse> getHighRiskRequests() {
        return riskService.getHighRiskRequests();
    }
    @GetMapping("/age-over/{age}")
    public List<RiskSummaryResponse> getOlderThan(
            @PathVariable Integer age) {
        return riskService.getOlderThan(age);
    }
    @GetMapping("/smokers")
    public List<RiskSummaryResponse> getSmokers() {
        return riskService.getSmokers();
    }
    @GetMapping("/high-bmi-smokers/{bmi}")
    public List<RiskSummaryResponse> getHighBmiSmokers(
            @PathVariable Double bmi) {
        return riskService.getHighBmiSmokers(bmi);
    }
    @GetMapping("/age/{age}")
    public List<RiskSummaryResponse> getByAge(
            @PathVariable Integer age) {
        return riskService.getByAge(age);
    }
    @PostMapping("/premium")
    public PremiumResponse calculatePremium(
            @Valid @RequestBody RiskRequest request) {

        return riskService.calculatePremium(request);
    }

}
