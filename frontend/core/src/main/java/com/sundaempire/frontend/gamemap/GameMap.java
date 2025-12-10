package com.sundaempire.frontend.gamemap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.gamemap.tile.Tile;
import com.sundaempire.frontend.gamemap.tile.TileFactory;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private final Vector2 origin = new Vector2();
    private final Vector2 tileDimensions = new Vector2();
    private final float gridLineThickness;

    private final int columns;
    private final int rows;
    private final TileFactory tileFactory;

    private final List<Tile> tiles;

    private float scale;

    public GameMap(Vector2 origin, Vector2 tileDimensions, int columns, int rows, float gridLineThickness, TileFactory tileFactory) {
        this.origin.set(origin);
        this.tileDimensions.set(tileDimensions);
        this.columns = columns;
        this.rows = rows;
        this.gridLineThickness = gridLineThickness;
        this.tileFactory = tileFactory;
        scale = 1.0f;

        tiles = new ArrayList<Tile>(columns * rows);

        generateTiles();
    }

    private void generateTiles() {
        for (int tileIndex = tiles.size(); tileIndex < rows * columns; ++tileIndex) {
            tiles.add(new Tile());
        }
    }

    private Vector2 getOriginCentered() {
        Vector2 originCentered = new Vector2(origin);
        Vector2 originOffset = (new Vector2(tileDimensions.x * columns, tileDimensions.y * rows)).scl(0.5f * scale);

        return originCentered.sub(originOffset);
    }

    public void setOrigin(Vector2 origin) {
        this.origin.set(origin);
    }

    public void setTileDimensions(Vector2 tileDimensions) {
        this.tileDimensions.set(tileDimensions);
    }

    public Tile getTile(int column, int row) {
        return tiles.get(columns * row + column);
    }

    public void setTile(int column, int row, Tile tile) {
        tiles.set(columns * row + column, tile);
    }

    public void registerInputProcessor(InputMultiplexer inputMultiplexer) {
        inputMultiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean scrolled(float amountX, float amountY) {
                scale -= amountY / 2f;

                if (scale < 0.2f) {
                    scale = 0.2f;
                }

                if (scale > 10f) {
                    scale = 10f;
                }

                return true;
            }
        });
        inputMultiplexer.addProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
            @Override
            public boolean pan(float x, float y, float deltaX, float deltaY) {
                origin.add(deltaX, -deltaY);

                return true;
            }
        }));
    }

    public void update() {
        Vector2 originCentered = getOriginCentered();
        Vector2 scaledTileDimensions = (new Vector2(tileDimensions)).scl(scale);

        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                Vector2 tilePosition = (new Vector2(column * scaledTileDimensions.x, row * scaledTileDimensions.y)).add(originCentered);
                Tile tile = tileFactory.createRandom(tilePosition, scaledTileDimensions);
                setTile(column, row, tile);
            }
        }
    }

    public void render(SpriteBatch batch) {
        // Render tiles.
        for (Tile tile : tiles) {
            tile.render(batch);
        }

        // Render the grid.
        renderGrid(batch);
    }

    private void renderGrid(SpriteBatch spriteBatch) {
        // Initialize a texture with the color we want.
        Pixmap colorPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        colorPixmap.setColor(1f, 1f, 1f, 0.5f);
        colorPixmap.fill();
        Texture colorTexture = new Texture(colorPixmap);
        colorPixmap.dispose();

        // Get the map dimensions.
        Vector2 mapDimensions = (new Vector2(tileDimensions)).scl(columns, rows).scl(scale);

        Vector2 scaledTileDimensions = (new Vector2(tileDimensions)).scl(scale);

        // Get the origin adjusted for centering.
        Vector2 originCentered = getOriginCentered();

        for (int row = 0; row <= rows; ++row) {
            spriteBatch.draw(colorTexture, originCentered.x - gridLineThickness / 2f, originCentered.y + row * scaledTileDimensions.y - gridLineThickness / 2f, mapDimensions.x + gridLineThickness, gridLineThickness);
        }

        for (int column = 0; column <= columns; ++column) {
            spriteBatch.draw(colorTexture, originCentered.x + column * scaledTileDimensions.x - gridLineThickness / 2f, originCentered.y - gridLineThickness / 2f, gridLineThickness, mapDimensions.y + gridLineThickness);
        }

    }

    public void dispose() {
        for (Tile tile : tiles) {
            tile.dispose();
        }
    }
}
