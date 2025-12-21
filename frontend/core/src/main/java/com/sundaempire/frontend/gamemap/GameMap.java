package com.sundaempire.frontend.gamemap;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
    private final OrthographicCamera camera;

    private final List<Tile> tiles;
    private Tile selectedTile;

    private float zoom;

    public GameMap(Vector2 origin, Vector2 tileDimensions, int columns, int rows, float gridLineThickness, TileFactory tileFactory, OrthographicCamera camera) {
        this.origin.set(origin);
        this.tileDimensions.set(tileDimensions);
        this.columns = columns;
        this.rows = rows;
        this.gridLineThickness = gridLineThickness;
        this.tileFactory = tileFactory;
        this.camera = camera;
        zoom = 1.0f;

        tiles = new ArrayList<Tile>(columns * rows);

        generateTiles();
    }

    private void generateTiles() {
        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                tiles.add(tileFactory.createRandom(column, row));
            }
        }
    }

    private Vector2 getOriginCentered() {
        Vector2 origin = new Vector2(this.origin);
        Vector2 originOffset = (new Vector2(tileDimensions.x * columns, tileDimensions.y * rows)).scl(0.5f * zoom);

        return origin.sub(originOffset);
    }

    public Vector2 getPositionFromCoordinates(Vector2 coordinates) {
        Vector2 position = new Vector2(coordinates);
        return position.scl(tileDimensions).add(getOriginCentered());
    }

    public boolean isCoordinateInBounds(Vector2 coordinates) {
        return coordinates.x >= 0 && coordinates.x < columns && coordinates.y >= 0 && coordinates.y < rows;
    }

    public void setOrigin(Vector2 origin) {
        this.origin.set(origin);
    }

    public Vector2 getTileDimensions() {
        return tileDimensions;
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
                GameMap.this.zoom(amountY);
                return true;
            }
        });

        inputMultiplexer.addProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
            @Override
            public boolean pan(float x, float y, float deltaX, float deltaY) {
                GameMap.this.pan(-deltaX, deltaY);
                return true;
            }

            @Override
            public boolean tap(float x, float y, int count, int button) {
                if (button == Input.Buttons.LEFT) {
                    if (count == 1) {
                        Vector3 input = camera.unproject(new Vector3(x, y, 0f));

                        GameMap.this.selectTile(input.x, input.y);
                    }
                }

                return true;
            }
        }));
    }

    private void zoom(float amount) {
        zoom = MathUtils.clamp(zoom + amount / 4f, 0.2f, 10f);

        camera.zoom = zoom;
    }

    private void pan(float deltaX, float deltaY) {
        camera.translate(deltaX * zoom, deltaY * zoom);
    }

    private void selectTile(float x, float y) {
        if (selectedTile != null) {
            selectedTile.setSelected(false);
        }

        Vector2 originCentered = getOriginCentered();

        float relativeX = x - originCentered.x;
        float relativeY = y - originCentered.y;

        int column = (int)(relativeX / tileDimensions.x);
        int row = (int)(relativeY / tileDimensions.y);

        int tileIndex = columns * row + column;

        if (0 <= tileIndex && tileIndex < tiles.size()) {
            selectedTile = tiles.get(tileIndex);
            selectedTile.setSelected(true);
        }
    }

    public void update() {
        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                getTile(column, row).setPosition(getOriginCentered().add(column * tileDimensions.x, row * tileDimensions.y));
                getTile(column, row).setDimensions(tileDimensions);
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

        for (Tile tile : tiles) {
            tile.renderSelectedBox(batch);
        }
    }

    private void renderGrid(SpriteBatch spriteBatch) {
        // Initialize a texture with the color we want.
        Pixmap gridLinePixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        gridLinePixmap.setColor(1f, 1f, 1f, 0.5f);
        gridLinePixmap.fill();
        Texture gridLineTexture = new Texture(gridLinePixmap);
        gridLinePixmap.dispose();

        // Get the map dimensions.
        Vector2 mapDimensions = (new Vector2(tileDimensions)).scl(columns, rows);
        Vector2 originCentered = getOriginCentered();

        for (int row = 0; row <= rows; ++row) {
            spriteBatch.draw(gridLineTexture, originCentered.x - gridLineThickness / 2f, originCentered.y + row * tileDimensions.y - gridLineThickness / 2f, mapDimensions.x + gridLineThickness, gridLineThickness);
        }

        for (int column = 0; column <= columns; ++column) {
            spriteBatch.draw(gridLineTexture, originCentered.x + column * tileDimensions.x - gridLineThickness / 2f, originCentered.y - gridLineThickness / 2f, gridLineThickness, mapDimensions.y + gridLineThickness);
        }

    }

    public void dispose() {
        for (Tile tile : tiles) {
            tile.dispose();
        }
    }
}
