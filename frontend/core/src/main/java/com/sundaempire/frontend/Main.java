package com.sundaempire.frontend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemap.GameMap;
import com.sundaempire.frontend.gamemap.tile.TileFactory;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.UnitFactory;
import com.sundaempire.frontend.unit.UnitPool;
import com.sundaempire.frontend.unit.*;

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
    private UnitPool unitPool;
    private Unit exampleUnit;

    private List<Unit> units = new ArrayList<>();
    private ShapeRenderer shapeRenderer;

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
        shapeRenderer = new ShapeRenderer();

        GameManager.INSTANCE.loadAssets();

        // Wait for the game manager to finish loading assets.
        GameManager.INSTANCE.getAssetManager().finishLoading();

        tileFactory = new TileFactory(TimeUtils.millis(), 4);
        gameMap = new GameMap(new Vector2(screenWidth / 2f, screenHeight / 2f), TILE_DIMENSIONS, GAME_MAP_COLUMNS, GAME_MAP_ROWS, GAME_MAP_GRID_LINE_THICKNESS, tileFactory, camera);
        gameMap.registerInputProcessor(inputMultiplexer);

        //unit system
        unitFactory = new UnitFactory();
        unitPool = new UnitPool();

        Unit swordsman = unitPool.obtain(unitFactory, UnitFactory.UnitType.SWORDSMAN, Unit.Owner.Player);
        swordsman.setPosition(new Vector2(50, 50));
        units.add(swordsman);

        Unit explorer = unitPool.obtain(unitFactory, UnitFactory.UnitType.EXPLORER, Unit.Owner.Player);
        explorer.setPosition(new Vector2(100, 50));
        units.add(explorer);

        Unit aiUnit = unitPool.obtain(unitFactory, UnitFactory.UnitType.ARCHER, Unit.Owner.AI);
        aiUnit.setPosition(new Vector2(200, 200));
        units.add(aiUnit);

        // start turn semua unitnya
        for (Unit unit : units) {
            unit.startTurn();
        }

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        gameMap.update();

        for (Unit unit : units) {
            unit.update(Gdx.graphics.getDeltaTime());
        }

        handleInput();

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        gameMap.render(batch);
        batch.end();

        // render unit sebagai kotak
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Unit unit : units) {
            if (unit.getOwner() == Unit.Owner.Player) {
                shapeRenderer.setColor(0f, 1f, 0f, 1f); // hijau = player
            } else {
                shapeRenderer.setColor(1f, 0f, 0f, 1f); // merah = AI
            }
            shapeRenderer.rect(unit.getPosition().x, unit.getPosition().y, 20, 20);
        }
        shapeRenderer.end();
    }

    private void handleInput() {
        if (units.isEmpty()) return;;

        // cth gerakkan unit player pertama dengan arrow keys
        Unit active = units.get(0); // misalnya swordsman
        Vector2 delta = new Vector2();

        if (active.canMove()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) delta.y += 20;
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) delta.y -= 20;
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) delta.x -= 20;
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) delta.x += 20;

            if (!delta.isZero()) active.move(delta);
        }
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

        for (Unit unit : units) unitPool.free(unit);
    }
}
