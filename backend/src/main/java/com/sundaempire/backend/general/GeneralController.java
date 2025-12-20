package com.sundaempire.backend.general;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GeneralController {
    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Try /api/info.");
    }

    @GetMapping("/health")
    public ResponseEntity<?> getHealth() {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", LocalDateTime.now());
        responseBody.put("apiStatus", "UP");

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInfo() {
        Map<String, Object> responseBody = new HashMap<>();

        Map<String, Object> endpoints = new HashMap<>();
        endpoints.put("APIHealth", "/api/health");
        endpoints.put("APIInfo", "/api/info");
        endpoints.put("players", "/api/players");
        endpoints.put("saves", "/api/saves");

        responseBody.put("endpoints", endpoints);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
