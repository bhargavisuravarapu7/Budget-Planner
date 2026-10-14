package com.budget_planner.budget_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.budget_planner.budget_backend.model.Budget;
import com.budget_planner.budget_backend.repository.BudgetRepository;

@RestController
@RequestMapping("/api/budget")
@CrossOrigin(origins = "http://localhost:5173")
public class BudgetController {

    private final BudgetRepository budgetRepository;

    public BudgetController(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    // Get the saved monthly budget for a specific user
    @GetMapping
    public Budget getBudget(@RequestParam Long userId) {

        return budgetRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Budget budget = new Budget();
                    budget.setMonthlyLimit(10000);
                    budget.setUserId(userId);
                    return budget;
                });
    }

    // Save or update the monthly budget for a specific user
    @PostMapping
    public ResponseEntity<Budget> saveBudget(
            @RequestParam Long userId,
            @RequestBody Budget newBudget) {

        Budget budget = budgetRepository.findByUserId(userId)
                .orElseGet(Budget::new);

        budget.setUserId(userId);
        budget.setMonthlyLimit(newBudget.getMonthlyLimit());

        Budget savedBudget = budgetRepository.save(budget);

        return ResponseEntity.ok(savedBudget);
    }
}