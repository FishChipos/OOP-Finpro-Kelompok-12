package com.sundaempire.backend.save;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SaveRepository extends JpaRepository<@NonNull Save, @NonNull UUID> {
    Optional<Save> findSaveById(Long id);
    List<Save> findSavesByPlayerId(UUID playerId);
}
