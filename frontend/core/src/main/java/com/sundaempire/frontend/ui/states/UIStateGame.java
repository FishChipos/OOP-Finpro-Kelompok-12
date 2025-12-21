package com.sundaempire.frontend.ui.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.ui.UI;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.commands.UnitCommandMoveRight;

public class UIStateGame extends UIState {

    private InputAdapter inputProcessor;

    Label roundCounter;

    public UIStateGame(UI ui) {
        this.ui = ui;
    }

    @Override
    public void onEnter() {
        Table table = ui.getTable();
        Skin skin = ui.getSkin();

        Table gameViewportTable = new Table();

        roundCounter = new Label("", skin);
        updateRoundCounter();
        Label currentlyMoving = new Label("PLAYER 1", skin);

        gameViewportTable.row().expandY().top().padTop(20f);
        gameViewportTable.add(roundCounter).width(10f).padLeft(20f);
        gameViewportTable.add(currentlyMoving).expandX();

        Table sidepanelTable = new Table();

        table.debug();
        table.row().fill().expandY();
        table.add(gameViewportTable).expandX().left().top();
        table.add(sidepanelTable).width(150f).left().top();
    }

    @Override
    public void update(float deltaTime) {
        ui.getGameMap().update(deltaTime);

        for (Unit unit : ui.getUnits()) {
            unit.update(deltaTime);
        }
    }

    @Override
    public void render(Batch batch) {
        ui.getGameMap().render(batch);

        for (Unit unit : ui.getUnits()) {
            unit.render(batch);
        }
    }

    @Override
    public void registerInputProcessor(InputMultiplexer multiplexer) {
        inputProcessor = new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Unit unit = GameManager.INSTANCE.getRoundManager().getActiveUnit();

                if (button == Input.Buttons.RIGHT && unit != null) {
                    Vector3 worldPos = ui.getCamera().unproject(new Vector3(screenX, screenY, 0));
                    unit.getUnitState().setNextCommand(new UnitCommandMoveRight());
                    return true;
                }
                return false;
            }
        };

        multiplexer.addProcessor(0, inputProcessor);
    }

    @Override
    public void unregisterInputProcessor(InputMultiplexer multiplexer) {
        multiplexer.removeProcessor(inputProcessor);
        inputProcessor = null;
    }

    @Override
    public void notice() {
        updateRoundCounter();
    }

    private void updateRoundCounter() {
        roundCounter.setText("ROUND " + GameManager.INSTANCE.getRoundManager().getRound());
    }
}
