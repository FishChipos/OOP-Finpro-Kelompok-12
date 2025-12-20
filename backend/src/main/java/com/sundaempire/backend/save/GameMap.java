package com.sundaempire.backend.save;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "game_maps")
public class GameMap {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "save_id")
    @JsonIgnore
    private Save save;

    @OneToMany(mappedBy = "gameMap", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Tile> tiles;

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        for (Tile tile : tiles) {
            tile.setGameMap(this);
        }

        this.tiles = tiles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Save getSave() {
        return save;
    }

    public void setSave(Save save) {
        this.save = save;
    }
}
