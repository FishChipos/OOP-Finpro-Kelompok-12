package com.sundaempire.frontend.gamemap.tile;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.gamemap.GameMap;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private final Vector2 coordinates = new Vector2();
    private final Rectangle collider = new Rectangle();

    private GameMap gameMap;
    private TileEnvironment environment;
    private List<TileProp> props = new ArrayList<>();

    private boolean selected;

    private static Texture SELECT_BOX_TEXTURE;


    public Tile() {
        selected = false;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
        if (this.gameMap != null) {
            Vector2 pos = this.gameMap.getPositionFromCoordinates(coordinates);
            collider.setPosition(pos);
            if (environment != null) {
                environment.setPosition(pos);
            }
            for (TileProp prop : props) {
                if (prop != null) {
                    prop.setPosition(pos);
                }
            }
        }
    }

    public void setCoordinates(Vector2 coordinates) {
        this.coordinates.set(coordinates);
        if (gameMap != null) {
            Vector2 pos = gameMap.getPositionFromCoordinates(this.coordinates);
            collider.setPosition(pos);
            if (environment != null) {
                environment.setPosition(pos);
            }
            for (TileProp prop : props) {
                if (prop != null) {
                    prop.setPosition(pos);
                }
            }
        }
    }

    public void setDimensions(Vector2 dimensions) {
        this.collider.setSize(dimensions.x, dimensions.y);
        if (environment != null) {
            environment.setDimensions(dimensions);
        }
        for (TileProp prop : props) {
            if (prop != null) {
                prop.setDimensions(dimensions);
            }
        }
    }

    public void setEnvironment(TileEnvironment environment) {
        if (this.environment != null) {
            this.environment.dispose();
        }

        this.environment = environment;
        if (this.environment != null) {
            this.environment.setPosition(collider.getPosition(new Vector2()));
            this.environment.setDimensions(collider.getSize(new Vector2()));
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void addProp(TileProp prop) {
        if (prop == null) return;

        if (gameMap != null) {
            Vector2 pos = gameMap.getPositionFromCoordinates(coordinates);
            prop.setPosition(pos);
            prop.setDimensions(collider.getSize(new Vector2()));
        } else {
            prop.setPosition(collider.getPosition(new Vector2()));
            prop.setDimensions(collider.getSize(new Vector2()));
        }

        props.add(prop);
    }

    public void removeProp(TileProp prop) {
        if (prop != null) {
            prop.dispose();
            props.remove(prop);
        }
    }

    public void update(float delta) {
        if (gameMap == null) return;

        Vector2 pos = gameMap.getPositionFromCoordinates(coordinates);
        collider.setPosition(pos);

        if (environment != null) {
            environment.setPosition(pos);
        }

        for (TileProp prop : props) {
            if (prop != null) {
                prop.setPosition(pos);
            }
        }
    }

    public void render(Batch batch) {
        if (environment != null) {
            environment.render(batch);
        }

        for (TileProp prop : props) {
            if (prop != null) {
                prop.render(batch);
            }
        }
    }

    private static void ensureSelectBoxTexture() {
        if (SELECT_BOX_TEXTURE == null) {
            Pixmap selectBoxPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            selectBoxPixmap.setColor(1f, 1f, 1f, 1f);
            selectBoxPixmap.fill();
            SELECT_BOX_TEXTURE = new Texture(selectBoxPixmap);
            selectBoxPixmap.dispose();
        }
    }

    public void renderSelectedBox(Batch batch) {
        if (selected) {
            ensureSelectBoxTexture();

            batch.draw(SELECT_BOX_TEXTURE, collider.x - 1f, collider.y - 1f, collider.width + 2f, 2f);
            batch.draw(SELECT_BOX_TEXTURE, collider.x - 1f, collider.y - 1f, 2f, collider.height + 2f);
            batch.draw(SELECT_BOX_TEXTURE, collider.x - 1f, collider.y + collider.height - 1f, collider.width + 2f, 2f);
            batch.draw(SELECT_BOX_TEXTURE, collider.x + collider.width - 1f, collider.y - 1f, 2f, collider.height + 2f);
        }
    }

    public void dispose() {
        if (environment != null) {
            environment.dispose();
            environment = null;
        }

        for (TileProp prop : props) {
            if (prop != null) {
                prop.dispose();
            }
        }
        props.clear();
    }

    public TileEnvironment getEnvironment() {
        return environment;
    }

    public List<TileProp> getProps() {
        return props;
    }

    public Vector2 getCoordinates() {
        return coordinates;
    }

    public void setPosition(Vector2 tilePosition) {
        collider.setPosition(tilePosition);
    }
}
