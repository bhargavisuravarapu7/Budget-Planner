package com.budget_planner.budget_backend.controller;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")

public class HealthController {

    private final JdbcTemplate jdbcTemplate;

    public HealthController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/api/health")
    public Map<String, String> health() {

        String message = jdbcTemplate.queryForObject(
            "SELECT 'PostgreSQL connected successfully'",
            String.class
        );

        return Map.of(
            "application", "Budget Planner",
            "status", "Running",
            "database", message
        );
    }
}