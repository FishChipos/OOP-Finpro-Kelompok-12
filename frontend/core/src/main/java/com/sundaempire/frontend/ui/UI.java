package com.sundaempire.frontend.ui;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.sundaempire.frontend.Notifiable;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemap.GameMap;
import com.sundaempire.frontend.settlement.Settlement;
import com.sundaempire.frontend.ui.states.UIStateGame;
import com.sundaempire.frontend.ui.states.UIStateMainMenu;
import com.sundaempire.frontend.ui.states.UIState;
import com.sundaempire.frontend.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public enum UI implements Notifiable {
    INSTANCE;

    private UIState currentState;

    private final Stage stage = new Stage();
    private final Table table = new Table();
    private final Skin skin = new Skin();

    private OrthographicCamera camera;
    private InputMultiplexer multiplexer;
    private GameMap gameMap;
    private List<Unit> units;
    private List<Settlement> settlements = new ArrayList<>();

    public void initialize(OrthographicCamera camera, InputMultiplexer multiplexer, GameMap gameMap, List<Unit> units) {
        this.camera = camera;
        this.multiplexer = multiplexer;
        this.gameMap = gameMap;
        this.units = units;

        GameManager.INSTANCE.getRoundManager().addObserver(this);
        gameMap.addObserver(this);

        table.setFillParent(true);
        stage.addActor(table);
        multiplexer.addProcessor(0, stage);

        initializeSkin();

        setState(new UIStateMainMenu(this));
    }

    private void initializeSkin() {
        skin.add("default", new BitmapFont());

        Label.LabelStyle labelStyle = new Label.LabelStyle(skin.getFont("default"), Color.WHITE);
        skin.add("default", labelStyle);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(null, null, null, skin.getFont("default"));
        skin.add("default", textButtonStyle);
    }

    public void setState(UIState newState) {
        if (currentState != null) {
            currentState.unregisterInputProcessor(multiplexer);
            currentState.onExit();
        }

        currentState = newState;

        if (currentState != null) {
            currentState.registerInputProcessor(multiplexer);
            currentState.onEnter();
        }
    }

    public void update(float deltaTime) {
        if (currentState != null) {
            currentState.update(deltaTime);
        }

        stage.act(deltaTime);
    }

    public void render(Batch batch) {
        batch.begin();

        if (currentState != null) {
            currentState.render(batch);
        }

        batch.end();

        stage.draw();
    }

    public void dispose() {

    }

    @Override
    public void notice() {
        currentState.notice();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public Table getTable() {
        return table;
    }

    public Stage getStage() {
        return stage;
    }

    public Skin getSkin() {
        return skin;
    }

    public List<Settlement> getSettlements() {
        return settlements;
    }
}

