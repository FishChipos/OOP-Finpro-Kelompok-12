package com.sundaempire.frontend.gamemap.tile;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.gamemap.GameMap;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private final Rectangle collider = new Rectangle();

    private TileEnvironment environment;
    private List<TileProp> props = new ArrayList<>();

    private boolean selected;

    public Tile() {
        selected = false;
    }

    public void setPosition(Vector2 position) {
        this.collider.setPosition(position);
        if (environment != null) {
            environment.setPosition(position);
        }
    }

    public void setDimensions(Vector2 dimensions) {
        this.collider.setSize(dimensions.x, dimensions.y);
        if (environment != null) {
            environment.setDimensions(dimensions);
        }
    }

    public void setEnvironment(TileEnvironment environment) {
        if (this.environment != null) {
            this.environment.dispose();
        }

        this.environment = environment;
        this.environment.setPosition(collider.getPosition(new Vector2()));
        this.environment.setDimensions(collider.getSize(new Vector2()));
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void addProp(TileProp prop) {
        props.add(prop);
    }

    public void removeProp(TileProp prop) {
        prop.dispose();
        props.remove(prop);
    }

    public void render(SpriteBatch batch) {
        if (environment != null) {
            environment.render(batch);
        }

        for (TileProp prop : props) {
            prop.render(batch);
        }
    }

    public void renderSelectedBox(SpriteBatch batch) {
        if (selected) {
            Pixmap selectBoxPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            selectBoxPixmap.setColor(0.95f, 0.8f, 0.02f, 1f);
            selectBoxPixmap.fill();
            Texture selectBoxTexture = new Texture(selectBoxPixmap);
            selectBoxPixmap.dispose();

            batch.draw(selectBoxTexture, collider.x - 1f, collider.y - 1f, collider.width + 2f, 2f);

            batch.draw(selectBoxTexture, collider.x - 1f, collider.y - 1f, 2f, collider.height + 2f);

            batch.draw(selectBoxTexture, collider.x - 1f, collider.y + collider.height - 1f, collider.width + 2f, 2f);

            batch.draw(selectBoxTexture, collider.x + collider.width - 1f, collider.y - 1f, 2f, collider.height + 2f);
        }
    }

    public void dispose() {
        environment.dispose();

        for (TileProp prop : props) {
            prop.dispose();
        }
    }
}
