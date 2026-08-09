package com.project.insurance.app.service;

import com.project.insurance.app.dto.*;
import com.project.insurance.app.entity.InsuranceRequirements;
import com.project.insurance.app.exception.ResourceNotFoundException;
import com.project.insurance.app.mapper.RiskMapper;
import com.project.insurance.app.model.RiskLevel;
import com.project.insurance.app.repository.InsuranceRequirementsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RiskService {

    private final InsuranceRequirementsRepository repository;
    private final RiskCalculator riskCalculator;
    private final RiskMapper mapper;
    private final PremiumCalculator premiumCalculator;

    public RiskService(InsuranceRequirementsRepository repository,
                       RiskCalculator riskCalculator,
                       RiskMapper mapper, PremiumCalculator premiumCalculator) {
        this.repository = repository;
        this.riskCalculator = riskCalculator;
        this.mapper = mapper;
        this.premiumCalculator = premiumCalculator;
    }
    public PremiumResponse calculatePremium(
            RiskRequest request){
        int score = riskCalculator.calculateScore(
                request.getAge(),
                request.getSmoker(),
                request.getBmi(),
                request.getChronicDisease(),
                request.getIncome()
        );
        RiskLevel level = RiskLevel.fromScore(score);

        Double premium = premiumCalculator.calculatePremium(score);

        return new PremiumResponse(
                score,
                level,
                premium
        );
    }

    // CREATE
    public RiskDetailResponse create(RiskRequest request) {

        InsuranceRequirements entity = mapper.toEntity(request);

        InsuranceRequirements saved = repository.save(entity);

        return mapper.toDetail(saved);
    }

    // READ ALL (DTO)
    public List<RiskSummaryResponse> getAll() {
        return mapper.toSummaryList(repository.findAll());
    }

    public Page<RiskSummaryResponse> getAllPaged(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable =
                PageRequest.of(page, size, sort);

        return repository
                .findAll(pageable)
                .map(mapper::toSummary);
    }

    // READ BY ID
    public RiskDetailResponse getById(Long id) {

        InsuranceRequirements entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Risk kaydı bulunamadı. ID: " + id));

        return mapper.toDetail(entity);
    }

    // DELETE
    public void deleteById(Long id) {
        InsuranceRequirements request = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Risk kaydı bulunamadı. ID: " + id));

        repository.delete(request);
    }

    // UPDATE
    public RiskDetailResponse update(
            Long id,
            RiskRequest request) {

        InsuranceRequirements entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Risk kaydı bulunamadı. ID: " + id));

        mapper.updateEntity(request, entity);

        InsuranceRequirements updated = repository.save(entity);

        return mapper.toDetail(updated);
    }

    // RISK CALCULATION
    public RiskResponse calculateRisk(RiskRequest request) {

        int score = riskCalculator.calculateScore(
                request.getAge(),
                request.getSmoker(),
                request.getBmi(),
                request.getChronicDisease(),
                request.getIncome()
        );

        RiskLevel level = RiskLevel.fromScore(score);


        return new RiskResponse(score, level);
    }

    // HIGH RISK (entity -> DTO)
    public List<RiskSummaryResponse> getHighRiskRequests() {

        return mapper.toSummaryList(
                repository.findAll()
                        .stream()
                        .filter(request ->
                                riskCalculator.calculateScore(
                                        request.getAge(),
                                        request.getSmoker(),
                                        request.getBmi(),
                                        request.getChronicDisease(),
                                        request.getIncome()
                                ) >= 60)
                        .toList()
        );
    }

    // AGE FILTER (DTO dönüşümlü)
    public List<RiskSummaryResponse> getByAge(Integer age) {

        if (age < 0) {
            throw new IllegalArgumentException("Yaş negatif olamaz");
        }

        return mapper.toSummaryList(
                repository.findByAge(age)
        );
    }

    public List<RiskSummaryResponse> getHighBmiSmokers(Double bmi) {
        return mapper.toSummaryList(
                repository.findHighBmiSmokers(bmi));
    }
    public List<RiskSummaryResponse> getSmokers(){
        return mapper.toSummaryList(
                repository.findAllSmokers()
        );
    }
    public List<RiskSummaryResponse> getOlderThan(int age) {
        return mapper.toSummaryList(
                repository.findOlderThan(age)
        );
    }
}
