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

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setCoordinates(Vector2 coordinates) {
        this.coordinates.set(coordinates);
    }

    public void configure() {
        collider.setSize(gameMap.getTileDimensions().x, gameMap.getTileDimensions().y);
        texture = GameManager.INSTANCE.getAssetManager().get("textures/settlements/settlement.png");
    }

    public void update(float delta) {
        collider.setPosition(gameMap.getPositionFromCoordinates(coordinates));
    }

    public void render(Batch batch) {
        batch.draw(texture, collider.x, collider.y, collider.width, collider.height);
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public Vector2 getCoordinates() {
        return coordinates;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
