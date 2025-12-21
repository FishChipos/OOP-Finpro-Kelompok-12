package com.sundaempire.frontend.ui.states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.ui.UIState;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.commands.UnitCommandMoveRight;

public class UIGameState implements UIState {

    private InputAdapter inputProcessor;
    private final OrthographicCamera camera;

    public UIGameState(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {

    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void render() {
    }

    @Override
    public void registerInputProcessor(InputMultiplexer multiplexer) {
        inputProcessor = new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Unit unit = GameManager.INSTANCE.getRoundManager().getActiveUnit();

                if (button == Input.Buttons.RIGHT && unit != null) {
                    Vector3 worldPos = camera.unproject(new Vector3(screenX, screenY, 0));
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
}
