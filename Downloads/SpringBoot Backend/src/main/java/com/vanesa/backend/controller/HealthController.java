package com.vanesa.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HealthController {

    @GetMapping("/health")
    public Map<String, Object> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Jetpack Joyride Backend is running!");
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }

    @GetMapping("/info")
    public Map<String, Object> info() {
        Map<String, Object> response = new HashMap<>();
        response.put("application", "CS6_Vanesa_Backend");
        response.put("version", "1.0");
        response.put("description", "Backend for Jetpack Joyride Case Study");

        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("players", "/api/players");
        endpoints.put("scores", "/api/scores");
        endpoints.put("health", "/api/health");
        endpoints.put("info", "/api/info");
        endpoints.put("leaderboard", "/api/players/leaderboard/high-score");

        response.put("endpoints", endpoints);
        return response;
    }
}

