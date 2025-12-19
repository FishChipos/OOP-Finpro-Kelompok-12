package com.sundaempire.frontend.ui;

public interface UIState {
    void onEnter();
    void onExit();
    void update(float deltaTime);
    void render();
    void handleInput();
}
