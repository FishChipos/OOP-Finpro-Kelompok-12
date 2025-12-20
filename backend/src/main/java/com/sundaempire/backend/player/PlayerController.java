package com.sundaempire.backend.player;

import com.sundaempire.backend.BackendErrorCompatible;
import org.apache.coyote.Response;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/players")
public class PlayerController implements BackendErrorCompatible {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<@NonNull List<Player>> getPlayers() {
        List<Player> players = playerService.findPlayers();

        return ResponseEntity.status(HttpStatus.OK).body(players);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable UUID id) {
        try {
            Player player = playerService.findPlayerById(id);
            return ResponseEntity.status(HttpStatus.OK).body(player);
        }
        catch (RuntimeException e) {
            return parseError(e);
        }
    }

    @GetMapping("username/{username}")
    public ResponseEntity<?> getPlayerByUsername(@PathVariable String username) {
        try {
            Player player = playerService.findPlayerByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(player);
        }
        catch (RuntimeException e) {
            return parseError(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> postPlayer(@RequestBody Player player) {
        try {
            Player createdPlayer = playerService.createPlayer(player);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
        }
        catch (RuntimeException e) {
            return parseError(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putPlayerById(@PathVariable UUID id, @RequestBody Player player) {
        try {
            Player updatedPlayer = playerService.updatePlayerById(id, player);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPlayer);
        }
        catch (RuntimeException e) {
            return parseError(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayerById(@PathVariable UUID id) {
        try {
            playerService.deletePlayerById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (RuntimeException e) {
            return parseError(e);
        }
    }

}
