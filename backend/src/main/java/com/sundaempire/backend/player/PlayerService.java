package com.sundaempire.backend.player;

import com.sundaempire.backend.BackendErrorCompatible;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService implements BackendErrorCompatible {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findPlayers() {
        return playerRepository.findAll();
    }

    public Player findPlayerById(UUID id) {
        Optional<Player> player = playerRepository.findPlayerById(id);

        if (player.isEmpty()) {
            throw createError(HttpStatus.NOT_FOUND, "ID not found!");
        }

        return player.get();
    }

    public Player findPlayerByUsername(String username) {
        Optional<Player> player = playerRepository.findPlayerByUsername(username);

        if (player.isEmpty()) {
            throw createError(HttpStatus.NOT_FOUND, "Username not found!");
        }

        return player.get();
    }

    public Player createPlayer(Player player) {
        if (player == null) {
            throw createError(HttpStatus.BAD_REQUEST, "Player is null!");
        }

        if (player.getUsername() == null) {
            throw createError(HttpStatus.BAD_REQUEST, "Username is null!");
        }

        if (playerRepository.existsByUsername(player.getUsername())) {
            throw createError(HttpStatus.CONFLICT, "Username already exists!");
        }

        return playerRepository.save(player);
    }

    @Transactional
    public void deletePlayerById(UUID id) {
        if (!playerRepository.existsById(id)) {
            throw createError(HttpStatus.NOT_FOUND, "ID not found!");
        }

        Player player = playerRepository.findPlayerById(id).get();
        playerRepository.delete(player);
    }

    @Transactional
    public Player updatePlayerById(UUID id, Player updatedPlayer) {
        Optional<Player> player = playerRepository.findPlayerById(id);

        if (player.isEmpty()) {
            throw createError(HttpStatus.NOT_FOUND, "ID not found!");
        }

        if (updatedPlayer == null) {
            throw createError(HttpStatus.BAD_REQUEST, "Player is null!");
        }

        if (updatedPlayer.getUsername() == null) {
            throw createError(HttpStatus.BAD_REQUEST, "Username is null!");
        }

        if (playerRepository.existsByUsername(updatedPlayer.getUsername())) {
            throw createError(HttpStatus.CONFLICT, "Username already exists!");
        }

        updatedPlayer.setId(player.get().getId());
        updatedPlayer.setCreatedAt(player.get().getCreatedAt());
        return playerRepository.save(updatedPlayer);
    }
}
