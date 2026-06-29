package com.project.insurance.app.repository;

import com.project.insurance.app.entity.InsuranceRequirements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRequirementsRepository
        extends JpaRepository<InsuranceRequirements, Long> {
    List<InsuranceRequirements> findByAge(Integer age);
    @Query("""
       SELECT i
       FROM InsuranceRequirements i
       WHERE i.smoker = true
       """)
    List<InsuranceRequirements> findAllSmokers();
    @Query("""
       SELECT i
       FROM InsuranceRequirements i
       WHERE i.age > :age
       """)
    List<InsuranceRequirements> findOlderThan(
            @Param("age") Integer age);
    @Query("""
       SELECT i
       FROM InsuranceRequirements i
       WHERE i.smoker = true
       AND i.bmi > :bmi
       """)
    List<InsuranceRequirements> findHighBmiSmokers(
            @Param("bmi") double bmi);

}