package com.project.insurance.app.controller;

import com.project.insurance.app.dto.*;
import com.project.insurance.app.service.RiskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
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

    @GetMapping("/page")
    public Page<RiskSummaryResponse> getAllPaged(
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "Page 0'dan küçük olamaz")
            int page,

            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "Size en az 1 olmalıdır")
            @Max(value = 100, message = "Size en fazla 100 olabilir")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        return riskService.getAllPaged(page, size, sortBy, direction);
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
            @PathVariable @Min(0) Integer age) {
        return riskService.getOlderThan(age);
    }
    @GetMapping("/smokers")
    public List<RiskSummaryResponse> getSmokers() {
        return riskService.getSmokers();
    }
    @GetMapping("/high-bmi-smokers/{bmi}")
    public List<RiskSummaryResponse> getHighBmiSmokers(
            @PathVariable @Positive Double bmi) {
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
