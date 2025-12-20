package com.sundaempire.frontend.unit;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Unit {

    protected Vector2 position;
    protected Vector2 size;

    protected UnitState currentState;

    public Unit(Vector2 position, Vector2 size) {
        this.position = new Vector2(position);
        this.size = new Vector2(size);
    }

    public void setState(UnitState newState) {
        if (currentState != null) {
            currentState.exit(this);
        }
        currentState = newState;
        if (currentState != null) {
            currentState.enter(this);
        }
    }

    public void update(float delta) {
        if (currentState != null) {
            currentState.update(this, delta);
        }
    }

    public abstract void render(SpriteBatch batch);

    public void dispose() {

    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position.set(position);
    }

    public Vector2 getSize() {
        return size;
    }
}
