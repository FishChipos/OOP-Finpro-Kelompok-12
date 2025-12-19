package com.sundaempire.frontend.ui.states;

import com.sundaempire.frontend.ui.UIState;
import com.sundaempire.frontend.ui.UI;

public class UIMainMenuState implements UIState {
    private final UI ui;

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
    public void render() {
    }

    @Override
    public void handleInput() {
    }

    public void startGame(UIState gameState) {
        ui.setState(gameState);
    }

    public void exitGame() {
        System.exit(0);
    }
}
