package com.sundaempire.frontend.ui.states;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.sundaempire.frontend.Notifiable;
import com.sundaempire.frontend.ui.UI;

public abstract class UIState implements Notifiable {
    protected UI ui;

    public abstract void onEnter();

    public void onExit() {
        ui.getTable().reset();
        ui.getTable().setFillParent(true);
    };
    public abstract void update(float deltaTime);
    public abstract void render(Batch batch);
    public abstract void registerInputProcessor(InputMultiplexer multiplexer);
    public abstract void unregisterInputProcessor(InputMultiplexer multiplexer);
}
