package com.sundaempire.backend.save;

import com.sundaempire.backend.BackendErrorCompatible;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SaveService implements BackendErrorCompatible {
    private final SaveRepository saveRepository;

    public SaveService(SaveRepository saveRepository) {
        this.saveRepository = saveRepository;
    }

    public List<Save> findSaves() {
        return saveRepository.findAll();
    }

    public Save findSaveById(Long id) {
        Optional<Save> save = saveRepository.findSaveById(id);

        if (save.isEmpty()) {
            throw createError(HttpStatus.NOT_FOUND, "ID not found!");
        }

        return save.get();
    }

    public List<Save> findSavesByPlayerId(UUID playerId) {
        List<Save> saves = saveRepository.findSavesByPlayerId(playerId);

        if (saves.isEmpty()) {
            throw createError(HttpStatus.NOT_FOUND, "Player ID not found!");
        }

        return saves;
    }
}
