package com.sundaempire.frontend.gamemap.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

abstract public class TileEnvironment {
    private final Vector2 position = new Vector2();
    private final Vector2 dimensions = new Vector2();

    private Texture texture;

    public void setPosition(Vector2 position) {
        this.position.set(position);
    }

    public void setDimensions(Vector2 dimensions) {
        this.dimensions.set(dimensions);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, dimensions.x, dimensions.y);
    }

    public void dispose() {
        texture.dispose();
    }
}
