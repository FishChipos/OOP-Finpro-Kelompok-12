package com.vanesa.backend.controller;

import com.vanesa.backend.model.Score; //sesuaikan
import com.vanesa.backend.service.PlayerService; // sesuaikan
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/players")
@CrossOrigin(origins = "*")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    public ResponseEntity<?> createdScore(@RequestBody Score score) {
        try {
            score createdScore = scoreService.createdScore(score);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdScore);
            return ResponseEntity
        }
    }

}