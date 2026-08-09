package com.project.insurance.app.controller;

import com.project.insurance.app.dto.*;
import com.project.insurance.app.service.RiskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/risk")
public class RiskController {

    private final RiskService riskService;



    public RiskController(RiskService riskService) {
        this.riskService = riskService;
    }

    @PostMapping
    public ResponseEntity<RiskDetailResponse> create(
            @Valid @RequestBody RiskRequest request) {

        RiskDetailResponse response = riskService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
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
    public RiskDetailResponse getById(
            @PathVariable Long id) {

        return riskService.getById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id) {

        riskService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public RiskDetailResponse update(
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
