package com.budget_planner.budget_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.budget_planner.budget_backend.model.SavingsGoal;

public interface SavingsGoalRepository
        extends JpaRepository<SavingsGoal, Long> {

    // Get all savings goals belonging to a specific user
    List<SavingsGoal> findByUserId(Long userId);

    // Find a savings goal only if it belongs to the given user
    Optional<SavingsGoal> findByIdAndUserId(Long id, Long userId);
}