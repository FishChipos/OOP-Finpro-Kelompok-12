package com.sundaempire.frontend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemap.GameMap;
import com.sundaempire.frontend.gamemap.tile.TileFactory;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private static final Vector2 TILE_DIMENSIONS = new Vector2(20f, 20f);
    private static final int GAME_MAP_COLUMNS = 10;
    private static final int GAME_MAP_ROWS = 10;
    private static final float GAME_MAP_GRID_LINE_THICKNESS = 2f;

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private InputMultiplexer inputMultiplexer;

    private TileFactory tileFactory;
    private GameMap gameMap;

    private float screenWidth, screenHeight;

    @Override
    public void create() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        inputMultiplexer = new InputMultiplexer();

        GameManager.INSTANCE.loadAssets();

        // Wait for the game manager to finish loading assets.
        while (!GameManager.INSTANCE.getAssetManager().update());

        tileFactory = new TileFactory();
        gameMap = new GameMap(new Vector2(screenWidth / 2f, screenHeight / 2f), TILE_DIMENSIONS, GAME_MAP_COLUMNS, GAME_MAP_ROWS, GAME_MAP_GRID_LINE_THICKNESS, tileFactory);
        gameMap.registerInputProcessor(inputMultiplexer);

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render() {
        gameMap.update();

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        gameMap.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        screenWidth = width;
        screenHeight = height;

        camera.setToOrtho(false);
        batch.setProjectionMatrix(camera.combined);

        gameMap.setOrigin(new Vector2(screenWidth / 2f, screenHeight / 2f));
    }

    @Override
    public void dispose() {
        batch.dispose();
        gameMap.dispose();
    }
}
