package com.sundaempire.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;

public interface ErrorResponseCompatible {
    default ErrorResponseException createErrorResponse(HttpStatus status, String message) {
        ErrorResponseException e = new ErrorResponseException(status);
        e.setDetail(message);
        return e;
    }

    default ResponseEntity<?> parseErrorResponse(ErrorResponseException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getBody());
    }
}
