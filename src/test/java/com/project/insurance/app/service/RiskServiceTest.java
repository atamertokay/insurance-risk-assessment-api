package com.project.insurance.app.service;

import com.project.insurance.app.dto.RiskDetailResponse;
import com.project.insurance.app.entity.InsuranceRequirements;
import com.project.insurance.app.exception.ResourceNotFoundException;
import com.project.insurance.app.mapper.RiskMapper;
import com.project.insurance.app.repository.InsuranceRequirementsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RiskServiceTest {
    @Mock
    private InsuranceRequirementsRepository repository;

    @Mock
    private RiskMapper mapper;

    @Mock
    private RiskCalculator riskCalculator;

    @Mock
    private PremiumCalculator premiumCalculator;

    @InjectMocks
    private RiskService riskService;

    @Test
    void shouldReturnRiskDetailWhenIdExists(){
    InsuranceRequirements entity =
            new InsuranceRequirements();

    entity.setId(1L);
    entity.setAge(25);
    entity.setBmi(22.0);

    RiskDetailResponse response =
            new RiskDetailResponse(
                    1L,
                    25,
                    false,
                    22.0,
                    100000.0,
                    false
            );
        when(repository.findById(1L))
            .thenReturn(Optional.of(entity));

        when(mapper.toDetail(entity))
            .thenReturn(response);
        RiskDetailResponse actual =
            riskService.getById(1L);
        assertEquals(
            response.getId(),
        actual.getId()
                );
        assertEquals(
                response.getAge(),
                actual.getAge()
        );

        assertEquals(
                response.getBmi(),
                actual.getBmi()
        );
        verify(repository, times(1))
                .findById(1L);

        verify(mapper, times(1))
                .toDetail(entity);
    }
    @Test
    void shouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        when(repository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(
                ResourceNotFoundException.class,
                () -> riskService.getById(1L)
        );
        verify(repository)
                .findById(1L);
    }




}
