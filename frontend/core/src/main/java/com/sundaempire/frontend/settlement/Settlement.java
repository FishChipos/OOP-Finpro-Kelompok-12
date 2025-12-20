package com.sundaempire.frontend.settlement;

import java.util.ArrayList;
import java.util.List;

public class Settlement {

    private int food;
    private int defense;

    private final List<Building> buildings = new ArrayList<>();

    public void addBuilding(Building building) {
        buildings.add(building);
        building.applyEffect(this);
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
