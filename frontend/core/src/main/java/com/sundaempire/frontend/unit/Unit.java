package com.sundaempire.frontend.unit;

import com.badlogic.gdx.math.Vector2;

public class Unit {

    public enum Owner { Player, AI }

    private String name;
    private int health;
    private int attack;

    private int maxMovementPoints;
    private int movementPoints;

    private UnitState currentState;

    private Owner owner;

    // posisii unit di map
    private Vector2 position;

    public Unit(String name, int health, int attack, int maxMovementPoints, Owner owner) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.maxMovementPoints = maxMovementPoints;
        this.movementPoints = maxMovementPoints;
        this.owner = owner;
        this.position = new Vector2(0, 0); // default awal
    }

    public Owner getOwner() { return owner; }
    public Vector2 getPosition() { return position; }
    public void setPosition(Vector2 position) { this.position = position; }

    public void startTurn() { movementPoints = maxMovementPoints; }
    public boolean canMove() { return movementPoints > 0; }
    public void move(Vector2 delta) {
        if (movementPoints > 0) {
            position.add(delta);
            movementPoints--;
        }
    }

    public void update(float delta) {
        if (currentState != null) {
            currentState.update(this, delta);
        }
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getAttack() { return attack; }
    public int getMovementPoints() { return movementPoints; }
    public void damage(int amount) { health -= amount; }
}
