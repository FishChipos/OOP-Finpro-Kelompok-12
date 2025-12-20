package com.sundaempire.backend.save;

import com.sundaempire.backend.ErrorResponseCompatible;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaveService implements ErrorResponseCompatible {
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
            throw createErrorResponse(HttpStatus.NOT_FOUND, "ID not found!");
        }

        return save.get();
    }

    public List<Save> findSavesByPlayerId(Long playerId) {
        List<Save> saves = saveRepository.findSavesByPlayerId(playerId);

        if (saves.isEmpty()) {
            throw createErrorResponse(HttpStatus.NOT_FOUND, "Player ID not found!");
        }

        return saves;
    }

    public Save createSave(Save save) {
        if (save == null) {
            throw createErrorResponse(HttpStatus.BAD_REQUEST, "Save is null!");
        }

        if (saveRepository.existsById(save.getId())) {
            throw createErrorResponse(HttpStatus.CONFLICT, "ID already exists!");
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
