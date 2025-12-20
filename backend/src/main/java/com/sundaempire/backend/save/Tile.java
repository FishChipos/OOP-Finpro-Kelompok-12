package com.sundaempire.backend.save;

import jakarta.persistence.*;

@Entity
@Table(name = "tiles")
public class Tile {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gamemap_id")
    private GameMap gameMap;
}
