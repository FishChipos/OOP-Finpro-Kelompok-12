package com.sundaempire.backend.player;

import com.sundaempire.backend.ErrorResponseCompatible;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements ErrorResponseCompatible {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findPlayers() {
        return playerRepository.findAll();
    }

    public Player findPlayerById(Long id) {
        Optional<Player> player = playerRepository.findPlayerById(id);

        if (player.isEmpty()) {
            throw createErrorResponse(HttpStatus.NOT_FOUND, "ID not found!");
        }

        return player.get();
    }

    public Player findPlayerByUsername(String username) {
        Optional<Player> player = playerRepository.findPlayerByUsername(username);

        if (player.isEmpty()) {
            throw createErrorResponse(HttpStatus.NOT_FOUND, "Username not found!");
        }

        return player.get();
    }

    public Player createPlayer(Player player) {
        if (player == null) {
            throw createErrorResponse(HttpStatus.BAD_REQUEST, "Player is null!");
        }

        if (player.getUsername() == null) {
            throw createErrorResponse(HttpStatus.BAD_REQUEST, "Username is null!");
        }

        if (playerRepository.existsByUsername(player.getUsername())) {
            throw createErrorResponse(HttpStatus.CONFLICT, "Username already exists!");
        }

        return playerRepository.save(player);
    }

    @Transactional
    public void deletePlayerById(Long id) {
        Optional<Player> player = playerRepository.findPlayerById(id);

        if (player.isEmpty()) {
            throw createErrorResponse(HttpStatus.NOT_FOUND, "ID not found!");
        }

        playerRepository.delete(player.get());
    }

    @Transactional
    public Player updatePlayerById(Long id, Player updatedPlayer) {
        Optional<Player> player = playerRepository.findPlayerById(id);

        if (player.isEmpty()) {
            throw createErrorResponse(HttpStatus.NOT_FOUND, "ID not found!");
        }

        if (updatedPlayer == null) {
            throw createErrorResponse(HttpStatus.BAD_REQUEST, "Player is null!");
        }

        if (updatedPlayer.getUsername() == null) {
            throw createErrorResponse(HttpStatus.BAD_REQUEST, "Username is null!");
        }

        if (playerRepository.existsByUsername(updatedPlayer.getUsername())) {
            throw createErrorResponse(HttpStatus.CONFLICT, "Username already exists!");
        }

        updatedPlayer.setId(player.get().getId());
        updatedPlayer.setCreatedAt(player.get().getCreatedAt());

        return playerRepository.save(updatedPlayer);
    }
}
