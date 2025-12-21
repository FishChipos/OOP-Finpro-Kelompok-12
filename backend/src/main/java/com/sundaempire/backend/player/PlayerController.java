package com.sundaempire.backend.player;

import com.sundaempire.backend.ErrorResponseCompatible;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/players")
public class PlayerController implements ErrorResponseCompatible {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam(required = false) String username) {
        if (username == null) {
            return getPlayers();
        }
        else {
            return getPlayerByUsername(username);
        }
    }

    private ResponseEntity<@NonNull List<Player>> getPlayers() {
        List<Player> players = playerService.findPlayers();

        return ResponseEntity.status(HttpStatus.OK).body(players);
    }

    private ResponseEntity<?> getPlayerByUsername(String username) {
        try {
            Player player = playerService.findPlayerByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(player);
        }
        catch (ErrorResponseException e) {
            return parseErrorResponse(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable Long id) {
        try {
            Player player = playerService.findPlayerById(id);
            return ResponseEntity.status(HttpStatus.OK).body(player);
        }
        catch (ErrorResponseException e) {
            return parseErrorResponse(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> postPlayer(@RequestBody Player player) {
        try {
            Player createdPlayer = playerService.createPlayer(player);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
        }
        catch (ErrorResponseException e) {
            return parseErrorResponse(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putPlayerById(@PathVariable Long id, @RequestBody Player player) {
        try {
            Player updatedPlayer = playerService.updatePlayerById(id, player);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(updatedPlayer);
        }
        catch (ErrorResponseException e) {
            return parseErrorResponse(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayerById(@PathVariable Long id) {
        try {
            playerService.deletePlayerById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (ErrorResponseException e) {
            return parseErrorResponse(e);
        }
    }
}
