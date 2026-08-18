package com.project.insurance.app.controller;

import com.project.insurance.app.dto.*;
import com.project.insurance.app.service.RiskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(
        name = "Risk Management",
        description = "Operations related to insurance risk assessment"
)
@RestController
@RequestMapping("/api/risk")
public class RiskController {

    private final RiskService riskService;

    public RiskController(RiskService riskService) {
        this.riskService = riskService;
    }


    @Operation(
            summary = "Creating new applicant",
            description = "Adding new applicant to database"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Applicant created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            )
    })
    @PostMapping
    public ResponseEntity<RiskDetailResponse> create(
            @Valid @RequestBody RiskRequest request) {

        RiskDetailResponse response = riskService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }



    @Operation(
            summary = "Viewing all applicants",
            description = "Displaying all applicants in a paginated and sorted manner."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Applicants gets successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            )
    })
    @GetMapping("/page")
    public Page<RiskSummaryResponse> getAllPaged(
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "Page can't be less than 0")
            int page,

            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "Size must be at least 1")
            @Max(value = 100, message = "Size can be at most 100")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        return riskService.getAllPaged(page, size, sortBy, direction);
    }
    @Operation(
            summary = "Calculate insurance risk",
            description = "Calculates the risk score and risk level."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Risk calculated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            )
    })
    @PostMapping("/calculate")
    public RiskResponse calculateRisk(
            @Valid @RequestBody RiskRequest request) {

        return riskService.calculateRisk(request);
    }
    @Operation(
            summary = "Viewing one applicant",
            description = "Viewing a applicant, who have this id"
    )
    @GetMapping("/{id}")
    public RiskDetailResponse getById(
            @PathVariable Long id) {

        return riskService.getById(id);
    }

    @Operation(
            summary = "Deleting an applicant",
            description = "Deleting an applicant from a database, who have this id"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id) {

        riskService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    @Operation(
            summary = "Updating an applicant",
            description = "Updating an applicant from database, who have this id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Applicant updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            )
    })
    @PutMapping("/{id}")
    public RiskDetailResponse update(
            @PathVariable Long id,
            @Valid @RequestBody RiskRequest request) {

        return riskService.update(id, request);
    }
    @Operation(
            summary = "Viewing high risk applicants",
            description = "Viewing all high risk applicants from database"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Applicants gets successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            )
    })
    @GetMapping("/high")
    public List<RiskSummaryResponse> getHighRiskRequests() {
        return riskService.getHighRiskRequests();
    }
    @Operation(
            summary = "Viewing older applicants",
            description = "Viewing applicants, who older than chosing age"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Applicants gets successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            )
    })
    @GetMapping("/age-over/{age}")
    public List<RiskSummaryResponse> getOlderThan(
            @PathVariable @Min(0) Integer age) {
        return riskService.getOlderThan(age);
    }
    @Operation(
            summary = "Viewing smoker applicant"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Smoker applicant gets successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            )
    })
    @GetMapping("/smokers")
    public List<RiskSummaryResponse> getSmokers() {
        return riskService.getSmokers();
    }
    @Operation(
            summary = "Viewing high bmi and smoker applicants",
            description = "Viewing hihg bmi applicants, who use smoke"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "High bmi and smoker applicant gets successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            )
    })
    @GetMapping("/high-bmi-smokers/{bmi}")
    public List<RiskSummaryResponse> getHighBmiSmokers(
            @PathVariable @Positive Double bmi) {
        return riskService.getHighBmiSmokers(bmi);
    }
    @Operation(
            summary = "Viewing applicants of the selected age",
            description = "Viewing list of applicants of the selected age"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Applicant gets successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            )
    })
    @GetMapping("/age/{age}")
    public List<RiskSummaryResponse> getByAge(
            @PathVariable Integer age) {
        return riskService.getByAge(age);
    }
    @Operation(
            summary = "Premium calculation",
            description = "Premium calculation based on entered data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Premium calculated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            )
    })
    @PostMapping("/premium")
    public PremiumResponse calculatePremium(
            @Valid @RequestBody RiskRequest request) {

        return riskService.calculatePremium(request);
    }

}
