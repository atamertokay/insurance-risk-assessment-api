package com.project.insurance.app.mapper;


import com.project.insurance.app.dto.RiskRequest;
import com.project.insurance.app.dto.RiskSummaryResponse;
import com.project.insurance.app.entity.InsuranceRequirements;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface RiskMapper {

    InsuranceRequirements toEntity(RiskRequest request);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "age", source = "age")
    @Mapping(target = "bmi", source = "bmi")
    RiskSummaryResponse toSummary(InsuranceRequirements entity);

    List<RiskSummaryResponse> toSummaryList(
            List<InsuranceRequirements> entities);
}