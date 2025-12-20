package com.sundaempire.frontend.unit;

public class Unit {

    private String name;
    private int health;
    private int attack;

    private int maxMovementPoints;
    private int movementPoints;

    private UnitState currentState;

    public Unit(String name, int health, int attack, int maxMovementPoints) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.maxMovementPoints = maxMovementPoints;
        this.movementPoints = maxMovementPoints;
    }

    public void startTurn() {
        movementPoints = maxMovementPoints;
    }

    public boolean canMove() {
        return movementPoints > 0;
    }

    public void move() {
        if (movementPoints > 0) {
            movementPoints--;
        }
    }

    public void changeState(UnitState newState) {
        if (currentState != null) {
            currentState.exit(this);
        }
        currentState = newState;
        currentState.enter(this);
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

    public void damage(int amount) {
        health -= amount;
    }
}
