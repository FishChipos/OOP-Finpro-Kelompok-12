package com.sundaempire.frontend.ui;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class UIState {
    public abstract void onEnter();
    public abstract void onExit();
    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch batch);
    public abstract void registerInputProcessor(InputMultiplexer multiplexer);
    public abstract void unregisterInputProcessor(InputMultiplexer multiplexer);
}
