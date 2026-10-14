package com.budget_planner.budget_backend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "savings_goals")
public class SavingsGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double targetAmount;

    private Double savedAmount;

    private LocalDate targetDate;

    // ID of the user who owns this savings goal
    private Long userId;

    public SavingsGoal() {
    }

    public SavingsGoal(String name, Double targetAmount,
                       Double savedAmount, LocalDate targetDate,
                       Long userId) {
        this.name = name;
        this.targetAmount = targetAmount;
        this.savedAmount = savedAmount;
        this.targetDate = targetDate;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Double getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(Double savedAmount) {
        this.savedAmount = savedAmount;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}