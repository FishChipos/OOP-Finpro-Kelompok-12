package com.sundaempire.frontend.ui.states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.sundaempire.frontend.ui.UI;
import com.sundaempire.frontend.ui.UIState;
import com.sundaempire.frontend.unit.Unit;

public class UIGameState implements UIState {

    private final UI ui;
    private Unit selectedUnit;
    private InputAdapter inputProcessor;
    private final OrthographicCamera camera;

    public UIGameState(OrthographicCamera camera, UI ui) {
        this.camera = camera;
        this.ui = ui;
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
        selectedUnit = null;
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void render() {
        SpriteBatch batch = ui.getBatch();
        BitmapFont font = ui.getFont();

        batch.begin();

        font.draw(batch, "GAME RUNNING", 20, 460);
        font.draw(batch, "Right Click : Move Unit", 20, 440);

        if (selectedUnit != null) {
            font.draw(batch, "Selected Unit", 20, 410);
            font.draw(batch, "Position: (" + (int) selectedUnit.getPosition().x + ", " + (int) selectedUnit.getPosition().y + ")", 20, 390);
        }
        else {
            font.draw(batch, "No Unit Selected", 20, 410);
        }
        batch.end();
    }

    @Override
    public void registerInputProcessor(InputMultiplexer multiplexer) {
        inputProcessor = new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button == Input.Buttons.RIGHT && selectedUnit != null) {
                    Vector3 worldPos = camera.unproject(new Vector3(screenX, screenY, 0));
                    selectedUnit.moveTo(worldPos.x, worldPos.y);
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

    public void selectUnit(Unit unit) {
        if (selectedUnit != null) {
            selectedUnit.deselect();
        }
        selectedUnit = unit;
        if (selectedUnit != null) {
            selectedUnit.select();
        }
    }
}
