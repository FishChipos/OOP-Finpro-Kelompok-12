package com.sundaempire.backend.save;

import com.sundaempire.backend.player.Player;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "saves")
public class Save {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "score", nullable = false, updatable = false)
    private Integer score;

    @OneToOne(mappedBy = "save", cascade = CascadeType.ALL)
    private GameMap gameMap;

    @OneToMany(mappedBy = "save", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Unit> units;

    @OneToMany(mappedBy = "save", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Settlement> settlements;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Save() {}

    public Save(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }
}
