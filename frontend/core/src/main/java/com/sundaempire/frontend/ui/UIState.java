package com.sundaempire.frontend.ui;

import com.badlogic.gdx.InputMultiplexer;

public interface UIState {
    void onEnter();
    void onExit();
    void update(float deltaTime);
    void render();
    void registerInputProcessor(InputMultiplexer multiplexer);
    void unregisterInputProcessor(InputMultiplexer multiplexer);
}
