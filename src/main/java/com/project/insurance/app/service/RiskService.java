package com.project.insurance.app.service;

import com.project.insurance.app.dto.PremiumResponse;
import com.project.insurance.app.dto.RiskRequest;
import com.project.insurance.app.dto.RiskResponse;
import com.project.insurance.app.dto.RiskSummaryResponse;
import com.project.insurance.app.entity.InsuranceRequirements;
import com.project.insurance.app.exception.ResourceNotFoundException;
import com.project.insurance.app.mapper.RiskMapper;
import com.project.insurance.app.model.RiskLevel;
import com.project.insurance.app.repository.InsuranceRequirementsRepository;
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
    };

    // CREATE
    public InsuranceRequirements create(RiskRequest request) {
        InsuranceRequirements entity = mapper.toEntity(request);
        return repository.save(entity);
    }

    // READ ALL (DTO)
    public List<RiskSummaryResponse> getAll() {
        return mapper.toSummaryList(repository.findAll());
    }

    // READ BY ID
    public InsuranceRequirements getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Risk kaydı bulunamadı. ID: " + id));
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
    public InsuranceRequirements update(Long id, RiskRequest request) {

        InsuranceRequirements entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Risk kaydı bulunamadı. ID: " + id));

        entity.setAge(request.getAge());
        entity.setSmoker(request.getSmoker());
        entity.setBmi(request.getBmi());
        entity.setIncome(request.getIncome());
        entity.setChronicDisease(request.getChronicDisease());

        return repository.save(entity);
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

        // Mapper kullanılabilir (istersen genişletiriz)
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
