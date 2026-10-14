package com.budget_planner.budget_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.budget_planner.budget_backend.model.Transaction;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    // Get all transactions belonging to a specific user
    List<Transaction> findByUserId(Long userId);

    // Find a transaction only if it belongs to the given user
    Optional<Transaction> findByIdAndUserId(Long id, Long userId);
}