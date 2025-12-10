package com.sundaempire.frontend.gamemap.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private final Vector2 position = new Vector2();
    private final Vector2 dimensions = new Vector2();

    private TileEnvironment environment;
    private List<TileProp> props = new ArrayList<>();

    public void setPosition(Vector2 position) {
        this.position.set(position);
    }

    public void setDimensions(Vector2 dimensions) {
        this.dimensions.set(dimensions);
    }

    public void setEnvironment(TileEnvironment environment) {
        if (this.environment != null) {
            this.environment.dispose();
        }

        this.environment = environment;
        this.environment.setPosition(position);
        this.environment.setDimensions(dimensions);
    }

    public void addProp(TileProp prop) {
        props.add(prop);
    }

    public void removeProp(TileProp prop) {
        prop.dispose();
        props.remove(prop);
    }

    public void render(SpriteBatch batch) {
        environment.render(batch);

        for (TileProp prop : props) {
            prop.render(batch);
        }
    }

    public void dispose() {
        environment.dispose();

        for (TileProp prop : props) {
            prop.dispose();
        }
    }
}
