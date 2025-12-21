package com.sundaempire.frontend.ui.states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sundaempire.frontend.ui.UI;
import com.sundaempire.frontend.ui.UIState;

public class UIMainMenuState extends UIState {

    private final UI ui;
    private InputAdapter inputProcessor;

    public UIMainMenuState(UI ui) {
        this.ui = ui;
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
    public void render(SpriteBatch batch) {
    }

    @Override
    public void registerInputProcessor(InputMultiplexer multiplexer) {
        inputProcessor = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.ENTER) {
                    ui.setState(new UIGameState(ui));
                    return true;
                }
                if (keycode == Input.Keys.ESCAPE) {
                    System.exit(0);
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
