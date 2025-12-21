package com.sundaempire.backend.save;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "units")
public class Unit {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "save_id")
    @JsonIgnore
    private Save save;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "player_controlled", nullable = false)
    private boolean playerControlled = true;

    @Column(name = "owner", nullable = false)
    @Enumerated(EnumType.STRING)
    private GameObjectOwner owner = GameObjectOwner.PLAYER_1;

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

    public GameObjectOwner getOwner() {
        return owner;
    }

    public void setOwner(GameObjectOwner owner) {
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPlayerControlled() {
        return playerControlled;
    }

    public void setPlayerControlled(boolean playerControlled) {
        this.playerControlled = playerControlled;
    }
}
