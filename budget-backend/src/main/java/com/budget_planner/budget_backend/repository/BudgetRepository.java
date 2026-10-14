package com.budget_planner.budget_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.budget_planner.budget_backend.model.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    // Get the budget belonging to a specific user
    Optional<Budget> findByUserId(Long userId);
}