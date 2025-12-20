package com.sundaempire.backend.save;

import jakarta.persistence.*;

@Entity
@Table(name = "settlements")
public class Settlement {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "save_id")
    private Save save;

    @Column(name = "player_controlled")
    private boolean playerControlled;
}
