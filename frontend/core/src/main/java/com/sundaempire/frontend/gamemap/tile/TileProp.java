package com.sundaempire.frontend.gamemap.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

abstract public class TileProp {
    private final Rectangle collider = new Rectangle();

    private final Texture texture;

    public TileProp(Texture texture) {
        this.texture = texture;
    }

    public void setPosition(Vector2 position) {
        collider.setPosition(position);
    }

    public void setDimensions(Vector2 dimensions) {
        collider.setSize(dimensions.x, dimensions.y);
    }

    public void render(Batch batch) {
        batch.draw(texture, collider.x, collider.y, collider.width, collider.height);
    }

    public void dispose() {
    }
}
