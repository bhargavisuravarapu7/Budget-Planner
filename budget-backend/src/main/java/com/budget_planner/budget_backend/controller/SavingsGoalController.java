package com.budget_planner.budget_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.budget_planner.budget_backend.model.SavingsGoal;
import com.budget_planner.budget_backend.repository.SavingsGoalRepository;

@RestController
@RequestMapping("/api/savings-goals")
@CrossOrigin(origins = "http://localhost:5173")
public class SavingsGoalController {

    private final SavingsGoalRepository savingsGoalRepository;

    public SavingsGoalController(
            SavingsGoalRepository savingsGoalRepository) {
        this.savingsGoalRepository = savingsGoalRepository;
    }

    // Get savings goals for a specific user
    @GetMapping
    public List<SavingsGoal> getAllGoals(
            @RequestParam Long userId) {

        return savingsGoalRepository.findByUserId(userId);
    }

    // Add a new savings goal for a specific user
    @PostMapping
    public SavingsGoal addGoal(
            @RequestParam Long userId,
            @RequestBody SavingsGoal goal) {

        goal.setUserId(userId);

        return savingsGoalRepository.save(goal);
    }

    // Update a savings goal belonging to a specific user
    @PutMapping("/{id}")
    public ResponseEntity<SavingsGoal> updateGoal(
            @PathVariable Long id,
            @RequestParam Long userId,
            @RequestBody SavingsGoal updatedGoal) {

        return savingsGoalRepository.findByIdAndUserId(id, userId)
                .map(goal -> {

                    goal.setName(updatedGoal.getName());
                    goal.setTargetAmount(updatedGoal.getTargetAmount());
                    goal.setSavedAmount(updatedGoal.getSavedAmount());
                    goal.setTargetDate(updatedGoal.getTargetDate());

                    SavingsGoal saved =
                            savingsGoalRepository.save(goal);

                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a savings goal belonging to a specific user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(
            @PathVariable Long id,
            @RequestParam Long userId) {

        return savingsGoalRepository.findByIdAndUserId(id, userId)
                .map(goal -> {
                    savingsGoalRepository.delete(goal);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}