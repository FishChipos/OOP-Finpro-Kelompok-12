package com.sundaempire.frontend.ui;

public class UI {
    private UIState currentState;

    public void setState(UIState state) {
        if (currentState != null) {
            currentState.onExit();
        }
        currentState = state;
        if (currentState != null) {
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

    public void handleInput() {
        if (currentState != null) {
            currentState.handleInput();
        }
    }
}
