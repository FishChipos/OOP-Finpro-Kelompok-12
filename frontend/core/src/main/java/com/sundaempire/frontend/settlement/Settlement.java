package com.sundaempire.frontend.settlement;

import com.sundaempire.frontend.faction.Faction;
import java.util.ArrayList;
import java.util.List;

public class Settlement {
    private Faction owner;
    private int food;
    private int defense;

    private final List<Building> buildings = new ArrayList<>();

    public Settlement(Faction owner) {
        this.owner = owner;
    }

    public boolean capture(Faction attacker, int attackPower) {
        if (attackPower > defense) {
            owner = attacker;
            defense = Math.max(0, defense / 2);
            return true;
        }
        return false;
    }

    public void addBuilding(Building building) {
        buildings.add(building);
        building.applyEffect(this);
    }

    public Faction getOwner() {
        return owner;
    }

    public void addFood(int amount) {
        food += amount;
    }

    public void addDefense(int amount) {
        defense += amount;
    }

    public int getFood() {
        return food;
    }

    public int getDefense() {
        return defense;
    }

    public List<Building> getBuildings() {
        return buildings;
    }
}
