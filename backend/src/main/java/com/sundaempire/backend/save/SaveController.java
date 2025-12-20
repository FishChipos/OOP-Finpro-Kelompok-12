package com.sundaempire.backend.save;

import com.sundaempire.backend.ErrorResponseCompatible;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/saves")
public class SaveController implements ErrorResponseCompatible {
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
        catch (ErrorResponseException e) {
            return parseErrorResponse(e);
        }
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<?> getSavesByPlayerId(@PathVariable Long playerId) {
        try {
            List<Save> saves = saveService.findSavesByPlayerId(playerId);
            return ResponseEntity.status(HttpStatus.OK).body(saves);
        }
        catch (ErrorResponseException e) {
            return parseErrorResponse(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> postSave(@RequestBody Save save) {
        try {
            Save createdSave = saveService.createSave(save);
            return ResponseEntity.status(HttpStatus.OK).body(createdSave);
        }
        catch (ErrorResponseException e) {
            return parseErrorResponse(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putSaveById(@PathVariable Long id, @RequestBody Save save) {
        try {
            Save updatedSave = saveService.updateSaveById(id, save);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(updatedSave);
        }
        catch (ErrorResponseException e) {
            return parseErrorResponse(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSaveById(@PathVariable Long id) {
        try {
            saveService.deleteSaveById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (ErrorResponseException e) {
            return parseErrorResponse(e);
        }
    }
}
