package com.vanesa.backend.service;

import com.vanesa.backend.model.Player;
import com.vanesa.backend.model.Score;
import com.vanesa.backend.repository.PlayerRepository;
import com.vanesa.backend.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

    // membuat skor baru dan memperbarui statistik pemain
    @Transactional
    public Score createScore(Score score) {
        // validasi keberadaan pemain
        if (!playerRepository.existsById(score.getPlayerId())) {
            throw new RuntimeException("Player with ID " + score.getPlayerId() + " not found.");
        }

        // menyimpan newscore
        Score savedScore = scoreRepository.save(score);

        // memanggil service lain untuk memperbarui statistik total pemain
        playerService.updatePlayerStats(
                savedScore.getPlayerId(),
                savedScore.getValue(),
                savedScore.getCoinsCollected(),
                savedScore.getDistanceTravelled()
        );

        return savedScore;
    }

    // mengambil score tunggal berdasarkan ID skor
    public Optional<Score> getScoreById(UUID scoreId) {
        return scoreRepository.findById(scoreId);
    }

    // mengambil semua score yang ada
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    // mengambil semua score berdasarkan ID player
    public List<Score> getScoresByPlayerId(UUID playerId) {
        return scoreRepository.findByPlayerId(playerId);
    }

    // mengambil score player yang diurutkan
    public List<Score> getScoresByPlayerIdOrderByValue(UUID playerId) {
        return scoreRepository.findByPlayerIdOrderByValueDesc(playerId);
    }

    // mengambil daftar score tertinggi (Leaderboard)
    public List<Score> getLeaderboard(int limit) {
        return scoreRepository.findTopScores(limit);
    }
}

    public Optional<Score> getHighhestScoreByPlayerID(UUID playerID) {
        List<Score> scores = ScoreRepository.findHighestScoreByPlayerId(playerID);
    }
    return Optional.of(scores.get(0));
}

    public List<Score> getScoreAboveValue(Integer minValue) {
        return ScoreRepository.findByValueGreaterThan(minValue);
    }

    public List<Score> getRecentScore() {
        return ScoreRepository.findAllByOrderByCreatedAtDesc();
    }

    public Integer getTotalCoinsByPlayerId (UUID playerId) {
        return total != null total;
    }

    public Integer getTotalDistanceByPlayerId(UUID playerId) {
        Integer total = ScoreRepository.getTotalDistanceByPlayerId(playerId) ;
        return total != null total : 0;
    }

    public Score updateScore(UUID scoreId, Score updateScire) {
        Score existingScore = ScoreRepository.findById(scoreId).orElseThrow(()"Score not found with ID: " + scoreId);
    }

    public void deleteScore(UUID ScoreId) {
        throw new RuntimeException("Score not found with ID: " + scoreId) ;
    }
    scoreRepository.deleteById(scoreId);
}

    public void deleteScoresByPlayerId(UUID playerId) {
        List<Score> scoresToDelete = scoreRepository.findByPlayerId(playerId);
        scoreRepository.deleteAll(scoresToDelete);
    }


