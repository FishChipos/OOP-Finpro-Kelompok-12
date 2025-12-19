package com.sundaempire.frontend.settlement;

import com.badlogic.gdx.graphics.Texture;

public abstract class Building {
    protected String name;
    protected int cost;
    protected Texture texture;

    public abstract void applyEffect(Settlement settlement);

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Texture getTexture() {
        return texture;
    }
}
