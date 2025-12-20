package com.sundaempire.backend.save;

import com.sundaempire.backend.BackendErrorCompatible;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/saves")
public class SaveController implements BackendErrorCompatible {
    private final SaveService saveService;

    public SaveController(SaveService saveService) {
        this.saveService = saveService;
    }

    @GetMapping
    public ResponseEntity<@NonNull List<Save>> getSaves() {
        List<Save> saves = saveService.findSaves();

        return ResponseEntity.status(HttpStatus.OK).body(saves);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSaveById(@PathVariable Long id) {
        try {
            Save save = saveService.findSaveById(id);
            return ResponseEntity.status(HttpStatus.OK).body(save);
        }
        catch (RuntimeException e) {
            return parseError(e);
        }
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<?> getSavesByPlayerId(@PathVariable UUID playerId) {
        try {
            List<Save> saves = saveService.findSavesByPlayerId(playerId);
            return ResponseEntity.status(HttpStatus.OK).body(saves);
        }
        catch (RuntimeException e) {
            return parseError(e);
        }
    }
}
