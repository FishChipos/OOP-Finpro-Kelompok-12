package com.vanesa.backend.controller;

import com.vanesa.backend.model.Player;
import com.vanesa.backend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class PlayerController {

    @Autowired
    private PlayerService playerService

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayer() {
        List<Player> = players
    }
}
