package com.sundaempire.backend.save;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface SaveRepository extends JpaRepository<@NonNull Save, @NonNull Long> {
    Optional<Save> findSaveById(Long id);
    List<Save> findSavesByPlayerId(Long playerId);
}
