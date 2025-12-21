package com.sundaempire.frontend.unit;

public class UnitStats {
    private String name;
    private int health;
    private int damage;
    private int maxMovement;
    private int range;
    private int cost;

    public UnitStats() {
        set("", 0, 0, 0, 0, 0);
    }

    public UnitStats(String name, int health, int damage, int maxMovement, int range, int cost) {
        set(name, health, damage, maxMovement, range, cost);
    }

    public void set(String name, int health, int damage, int maxMovement, int range, int cost) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.maxMovement = maxMovement;
        this.range = range;
        this.cost = cost;
    }

    public void set(UnitStats unitStats) {
        this.name = unitStats.getName();
        this.health = unitStats.getHealth();
        this.damage = unitStats.getDamage();
        this.maxMovement = unitStats.getMaxMovement();
        this.range = unitStats.getRange();
        this.cost = unitStats.getCost();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMaxMovement() {
        return maxMovement;
    }

    public void setMaxMovement(int maxMovement) {
        this.maxMovement = maxMovement;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
