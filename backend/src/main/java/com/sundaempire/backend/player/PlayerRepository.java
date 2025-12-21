package com.sundaempire.backend.player;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface PlayerRepository extends JpaRepository<@NonNull Player, @NonNull Long> {
    Optional<Player> findPlayerById(Long id);
    Optional<Player> findPlayerByUsername(String username);

    boolean existsByUsername(String username);
}
