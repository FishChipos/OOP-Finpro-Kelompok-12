package com.sundaempire.frontend.ui;

import com.badlogic.gdx.InputMultiplexer;

public class UI {

    private UIState currentState;
    private final InputMultiplexer multiplexer;

    public UI(InputMultiplexer multiplexer) {
        this.multiplexer = multiplexer;
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
    }

    public void render() {
        if (currentState != null) {
            currentState.render();
        }
    }
}

