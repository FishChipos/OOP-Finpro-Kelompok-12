package com.sundaempire.frontend.unit;


public class Unit {

    private String name;
    private int health;
    private int attack;

    private UnitState currentState;

    public Unit(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
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

    public void damage(int amount) {
        health -= amount;
    }
}

