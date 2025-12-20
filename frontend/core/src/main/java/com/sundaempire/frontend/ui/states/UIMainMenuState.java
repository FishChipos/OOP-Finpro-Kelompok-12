package com.sundaempire.frontend.ui.states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.sundaempire.frontend.ui.UI;
import com.sundaempire.frontend.ui.UIState;

public class UIMainMenuState implements UIState {

    private final UI ui;
    private final UIState gameState;
    private InputAdapter inputProcessor;

    public UIMainMenuState(UI ui, UIState gameState) {
        this.ui = ui;
        this.gameState = gameState;
    }

    @Override
    public void onEnter() {
        inputProcessor = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.ENTER) {
                    ui.setState(gameState);
                    return true;
                }
                if (keycode == Input.Keys.ESCAPE) {
                    System.exit(0);
                    return true;
                }
                return false;
            }
        };
    }

    @Override
    public void onExit() {
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
}
