package com.sundaempire.backend.save;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "gamemaps")
public class GameMap {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "save_id")
    private Save save;

    @OneToMany(mappedBy = "gameMap", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tile> tiles;
}
