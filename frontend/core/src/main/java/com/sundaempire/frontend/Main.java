package com.sundaempire.frontend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.sundaempire.frontend.gamemanager.GameActor;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemap.GameMap;
import com.sundaempire.frontend.gamemap.tile.TileFactory;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.UnitFactory;
import com.sundaempire.frontend.unit.UnitPool;
import com.sundaempire.frontend.unit.UnitType;

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

    private UnitFactory unitFactory;
    private UnitPool unitPool;
    private Unit explorer;
    private Unit swordsman;

    private float screenWidth, screenHeight;

    @Override
    public void create() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(screenWidth, screenHeight);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0f);
        camera.update();
        batch = new SpriteBatch();
        inputMultiplexer = new InputMultiplexer();

        GameManager.INSTANCE.loadAssets();

        // Wait for the game manager to finish loading assets.
        GameManager.INSTANCE.getAssetManager().finishLoading();

        tileFactory = new TileFactory(TimeUtils.millis(), 4);
        gameMap = new GameMap(new Vector2(screenWidth / 2f, screenHeight / 2f), TILE_DIMENSIONS, GAME_MAP_COLUMNS, GAME_MAP_ROWS, GAME_MAP_GRID_LINE_THICKNESS, tileFactory, camera);
        gameMap.registerInputProcessor(inputMultiplexer);

        //Unit system
        unitFactory = new UnitFactory();
        unitPool = new UnitPool(unitFactory, gameMap, inputMultiplexer);

        explorer = unitPool.obtain(new Vector2(0f, 0f), UnitType.EXPLORER, GameActor.PLAYER_1);
        swordsman = unitPool.obtain(new Vector2(0f, 0f), UnitType.SWORDSMAN, GameActor.PLAYER_1);
        GameManager.INSTANCE.getRoundManager().nextUnit();

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        gameMap.update();

        //update unit (logic only belum render)
        explorer.update(Gdx.graphics.getDeltaTime());
        swordsman.update(Gdx.graphics.getDeltaTime());

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        gameMap.render(batch);
        explorer.render(batch);
        swordsman.render(batch);
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

        unitPool.release(explorer);
    }
}
