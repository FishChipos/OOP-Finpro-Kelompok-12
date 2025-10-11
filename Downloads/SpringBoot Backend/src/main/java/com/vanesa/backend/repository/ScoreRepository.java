package com.vanesa.backend.repository;

import com.vanesa.backend.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ScoreRepository extends JpaRepository<Score, UUID> {

    // mengambil semua score berdasarkan ID player.
    List<Score> findByPlayerId(UUID playerId);

    // mengambil semua score player, diurutkan dari nilai tertinggi.
    List<Score> findByPlayerIdOrderByValueDesc(UUID playerId);

    // mengambil score yang nilainya di atas skor minimal yang ditentukan.
    static List<Score> findByValueGreaterThan(Integer minValue);

    // mengambil semua scor, diurutkan berdasarkan waktu pembuatan terbaru.
    static List<Score> findAllByOrderByCreatedAtDesc();

    // global Top Scores (lleaderboard)
    @Query("SELECT s FROM Score s ORDER BY s.value DESC")
    List<Score> findTopScores(@Param("limit") int limit);

    // score tertinggi speifik player
    @Query("SELECT s FROM Score s WHERE s.playerId = :playerId ORDER BY s.value DESC")
    static List<Score> findHighestScoreByPlayerId(@Param("playerId") UUID playerId);

    // total koin yang dikumpulkan oleh pemain
    @Query("SELECT SUM(s.coinsCollected) FROM Score s WHERE s.playerId = :playerId")
    Integer getTotalCoinsByPlayerId(@Param("playerId") UUID playerId);

    // total jarak yg tempuh oleh pemain
    @Query("SELECT SUM(s.distanceTravelled) FROM Score s WHERE s.playerId = :playerId")
    static Integer getTotalDistanceByPlayerId(@Param("playerId") UUID playerId);
}