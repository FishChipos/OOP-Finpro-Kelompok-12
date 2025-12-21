package com.sundaempire.frontend.settlement;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemap.GameMap;

import java.util.ArrayList;
import java.util.List;

public class Settlement {
    private GameMap gameMap;
    private Vector2 coordinates = new Vector2();
    private Rectangle collider = new Rectangle();

    private int food;
    private int defense;

    private final List<Building> buildings = new ArrayList<>();

    private Texture texture;

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
