package com.sundaempire.frontend.unit;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.gamemanager.GameActor;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemap.GameMap;
import com.sundaempire.frontend.unit.states.UnitStateIdle;

public class Unit {
    private UnitType unitType;
    private GameActor owner;

    private UnitState unitState = new UnitStateIdle(this);
    private GameMap gameMap;
    private InputMultiplexer inputMultiplexer;

    private final UnitStats unitStats = new UnitStats();
    private Vector2 coordinates = new Vector2();
    private Rectangle collider = new Rectangle();
    private Texture texture;

    private int movement;

    public void configure() {
        collider.setSize(gameMap.getTileDimensions().x, gameMap.getTileDimensions().y);
        unitStats.set(unitType.getUnitStats());
        texture = GameManager.INSTANCE.getAssetManager().get(unitType.getTexturePath());
    }

    public void changeUnitState(UnitState nextUnitState) {
        unitState.exit();
        unitState.deregisterInputProcessor(inputMultiplexer);
        unitState = nextUnitState;
        unitState.enter();
        unitState.registerInputProcessor(inputMultiplexer);
    }

    public void translate(Vector2 coordinateTranslation) {
        coordinates.add(coordinateTranslation);
    }

    public void update(float delta) {
        collider.setPosition(gameMap.getPositionFromCoordinates(coordinates));
        unitState.update(delta);
    }

    public void render(SpriteBatch batch) {
        unitState.render(batch);
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public GameActor getOwner() {
        return owner;
    }

    public void setOwner(GameActor owner) {
        this.owner = owner;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    public void setInputMultiplexer(InputMultiplexer inputMultiplexer) {
        this.inputMultiplexer = inputMultiplexer;
    }

    public UnitState getUnitState() {
        return unitState;
    }

    public UnitStats getUnitStats() {
        return unitStats;
    }

    public Rectangle getCollider() {
        return collider;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public Vector2 getPosition() {
        return new Vector2(collider.x, collider.y);
    }

    public void setPosition(Vector2 position) {
        collider.setPosition(position);
    }

    public Vector2 getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Vector2 coordinates) {
        this.coordinates = coordinates;
    }
}
