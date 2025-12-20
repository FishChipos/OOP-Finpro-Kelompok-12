package com.sundaempire.frontend.ui.states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.sundaempire.frontend.ui.UIState;
import com.sundaempire.frontend.unit.Unit;

public class UIGameState implements UIState {

    private Unit selectedUnit;
    private InputAdapter inputProcessor;
    private final OrthographicCamera camera;

    public UIGameState(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public void onEnter() {
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
    }

    @Override
    public void onExit() {
        selectedUnit = null;
        inputProcessor = null;
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void render() {
    }

    @Override
    public void registerInputProcessor(InputMultiplexer multiplexer) {
        multiplexer.addProcessor(inputProcessor);
    }

    @Override
    public void unregisterInputProcessor(InputMultiplexer multiplexer) {
        multiplexer.removeProcessor(inputProcessor);
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
