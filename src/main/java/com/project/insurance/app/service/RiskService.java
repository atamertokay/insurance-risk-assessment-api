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

        Double premium = premiumCalculator.calculatePremium(level);

        return new PremiumResponse(
                score,
                level,
                premium
        );
    }


    public RiskDetailResponse create(RiskRequest request) {

        InsuranceRequirements entity = mapper.toEntity(request);

        InsuranceRequirements saved = repository.save(entity);

        return mapper.toDetail(saved);
    }

    public Page<RiskSummaryResponse> getAllPaged(
            int page,
            int size,
            String sortBy,
            String direction) {
        if (page < 0) {
            throw new IllegalArgumentException("Page no can't be less than zero");
        }
        if (size < 1 || size > 100) {
            throw new IllegalArgumentException(
                    "Page size must be between 1 and 100"
            );
        }
        Sort sort;
        List<String> allowedSortFields =
                List.of("id", "age", "bmi", "income");
        if (!allowedSortFields.contains(sortBy)) {
            throw new IllegalArgumentException(
                    "invalid sort field: " + sortBy
            );
        }
        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else if(direction.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else {
            throw new IllegalArgumentException("The sort direction must be 'asc' or 'desc'.");
        }

        Pageable pageable =
                PageRequest.of(page, size, sort);

        return repository
                .findAll(pageable)
                .map(mapper::toSummary);
    }


    public RiskDetailResponse getById(Long id) {

        InsuranceRequirements entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No risk record found. ID: " + id));

        return mapper.toDetail(entity);
    }


    public void deleteById(Long id) {
        InsuranceRequirements request = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No risk record found. ID: " + id));

        repository.delete(request);
    }


    public RiskDetailResponse update(
            Long id,
            RiskRequest request) {

        InsuranceRequirements entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No risk record found. ID: " + id));

        mapper.updateEntity(request, entity);

        InsuranceRequirements updated = repository.save(entity);

        return mapper.toDetail(updated);
    }


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


    public List<RiskSummaryResponse> getByAge(Integer age) {

        if (age < 0) {
            throw new IllegalArgumentException("Age can't be less than zero");
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
