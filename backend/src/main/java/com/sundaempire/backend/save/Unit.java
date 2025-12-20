package com.sundaempire.backend.save;

import jakarta.persistence.*;

@Entity
@Table(name = "units")
public class Unit {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "save_id")
    private Save save;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "player_controlled", nullable = false)
    private boolean playerControlled;
}
