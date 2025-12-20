package com.sundaempire.backend.save;

import com.sundaempire.backend.ErrorResponseCompatible;
import com.sundaempire.backend.player.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SaveService implements ErrorResponseCompatible {
    private final PlayerRepository playerRepository;
    private final SaveRepository saveRepository;

    public SaveService(PlayerRepository playerRepository, SaveRepository saveRepository) {
        this.playerRepository = playerRepository;
        this.saveRepository = saveRepository;
    }

    public List<Save> findSaves() {
        return saveRepository.findAll();
    }

    public Save findSaveById(Long id) {
        Optional<Save> save = saveRepository.findSaveById(id);

        if (save.isEmpty()) {
            throw createErrorResponse(HttpStatus.NOT_FOUND, "ID not found!");
        }

        return save.get();
    }

    public List<Save> findSavesByPlayerId(Long playerId) {
        if (!playerRepository.existsById(playerId)) {
            throw createErrorResponse(HttpStatus.NOT_FOUND, "Player ID not found!");
        }

        return saveRepository.findSavesByPlayerId(playerId);
    }

    @Transactional
    public Save createSave(Save save) {
        if (save == null) {
            throw createErrorResponse(HttpStatus.BAD_REQUEST, "Save is null!");
        }

        if (!playerRepository.existsById(save.getPlayerId())) {
            throw createErrorResponse(HttpStatus.NOT_FOUND, "Player ID not found!");
        }

        return saveRepository.save(save);
    }

    @Transactional
    public Save updateSaveById(Long id, Save updatedSave) {
        Optional<Save> save = saveRepository.findSaveById(id);

        if (save.isEmpty()) {
            throw createErrorResponse(HttpStatus.NOT_FOUND, "ID not found!");
        }

        if (updatedSave == null) {
            throw createErrorResponse(HttpStatus.BAD_REQUEST, "Save is null!");
        }

        updatedSave.setId(save.get().getId());
        updatedSave.setCreatedAt(save.get().getCreatedAt());

        return saveRepository.save(updatedSave);
    }

    @Transactional
    public void deleteSaveById(Long id) {

        Optional<Save> save = saveRepository.findSaveById(id);

        if (save.isEmpty()) {
            throw createErrorResponse(HttpStatus.NOT_FOUND, "ID not found!");
        }

        saveRepository.delete(save.get());
    }
}
