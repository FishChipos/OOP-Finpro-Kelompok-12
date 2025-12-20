package com.sundaempire.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface BackendErrorCompatible {
    default RuntimeException createError(HttpStatus status, String message) {
        return new RuntimeException(status.toString() + " " + message);
    }

    default ResponseEntity<?> parseError(RuntimeException e) {
        String[] splitMessage = e.getMessage().split(" ", 2);
        return ResponseEntity.status(Integer.parseInt(splitMessage[0])).body(splitMessage[1]);
    }
}
