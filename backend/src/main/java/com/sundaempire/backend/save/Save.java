package com.sundaempire.backend.save;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sundaempire.backend.player.Player;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;

import java.lang.invoke.VolatileCallSite;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "saves")
public class Save {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "player_id")
    private Long playerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Player player;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "score", nullable = false, updatable = false)
    private Integer score = 0;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private GameMap gameMap;

    @OneToMany(mappedBy = "save", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Unit> units = new ArrayList<>();

    @OneToMany(mappedBy = "save", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Settlement> settlements = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Save() {}

    public Save(Integer score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public List<Settlement> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<Settlement> settlements) {
        this.settlements = settlements;
    }
}
