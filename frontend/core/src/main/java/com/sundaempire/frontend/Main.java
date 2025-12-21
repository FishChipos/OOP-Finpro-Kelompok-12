package com.sundaempire.frontend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sundaempire.frontend.gamemanager.GameActor;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemap.GameMap;
import com.sundaempire.frontend.gamemap.tile.TileFactory;
import com.sundaempire.frontend.ui.UI;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.UnitFactory;
import com.sundaempire.frontend.unit.UnitPool;
import com.sundaempire.frontend.unit.UnitType;
import com.sundaempire.frontend.backend.BackendService;

import java.util.ArrayList;
import java.util.List;

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
        UnitPool.INSTANCE.setGameMap(gameMap);
        UnitPool.INSTANCE.setInputMultiplexer(inputMultiplexer);
        UnitPool.INSTANCE.obtain(new Vector2(0f, 0f), UnitType.EXPLORER, GameActor.PLAYER_1);
        System.out.println("After obtain 1: round=" + GameManager.INSTANCE.getRoundManager().getRound() + ", player1 units=" + GameManager.INSTANCE.getPlayerManager().getPlayer(GameActor.PLAYER_1).getUnits().size());
        UnitPool.INSTANCE.obtain(new Vector2(0f, 1f), UnitType.SWORDSMAN, GameActor.PLAYER_1);
        System.out.println("After obtain 2: round=" + GameManager.INSTANCE.getRoundManager().getRound() + ", player1 units=" + GameManager.INSTANCE.getPlayerManager().getPlayer(GameActor.PLAYER_1).getUnits().size());
        UnitPool.INSTANCE.obtain(new Vector2(1f, 0f), UnitType.ARCHER, GameActor.PLAYER_1);
        System.out.println("After obtain 3: round=" + GameManager.INSTANCE.getRoundManager().getRound() + ", player1 units=" + GameManager.INSTANCE.getPlayerManager().getPlayer(GameActor.PLAYER_1).getUnits().size());
        GameManager.INSTANCE.getRoundManager().nextUnit();
        System.out.println("After nextUnit(): round=" + GameManager.INSTANCE.getRoundManager().getRound() + ", currentActor=" + GameManager.INSTANCE.getRoundManager().getCurrentGameActor() + ", player1 units=" + GameManager.INSTANCE.getPlayerManager().getPlayer(GameActor.PLAYER_1).getUnits().size());
        GameManager.INSTANCE.getRoundManager().nextUnit();

        UI.INSTANCE.initialize(camera, inputMultiplexer, gameMap, UnitPool.INSTANCE.getActiveUnits());

        BackendService.INSTANCE.getInfo(new BackendService.ResponseHandler() {
            @Override
            public void onSuccess(String response) {
                System.out.println("Backend /api/info response: " + response);
            }

            @Override
            public void onError(String error) {
                System.out.println("Backend /api/info error: " + error);
            }
        });

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        UI.INSTANCE.update(delta);

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        UI.INSTANCE.render(batch);
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

        UnitPool.INSTANCE.releaseAll();
        UI.INSTANCE.dispose();
    }
}
