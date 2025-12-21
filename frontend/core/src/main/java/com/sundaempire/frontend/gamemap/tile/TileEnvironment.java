package com.sundaempire.frontend.gamemap.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

abstract public class TileEnvironment {
    private final Rectangle collider = new Rectangle();

    private Texture texture;

    private String name;

    public void setPosition(Vector2 position) {
        collider.setPosition(position);
    }

    public void setDimensions(Vector2 dimensions) {
        collider.setSize(dimensions.x, dimensions.y);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void render(Batch batch) {
        if (texture != null) {
            batch.draw(texture, collider.x, collider.y, collider.width, collider.height);
        }
    }

    public void dispose() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
