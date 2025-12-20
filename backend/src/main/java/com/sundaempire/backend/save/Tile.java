package com.sundaempire.backend.save;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tiles")
public class Tile {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "game_map_id")
    private Long gameMapId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "game_map_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private GameMap gameMap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameMapId() {
        return gameMapId;
    }

    public void setGameMapId(Long gameMapId) {
        this.gameMapId = gameMapId;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }
}
