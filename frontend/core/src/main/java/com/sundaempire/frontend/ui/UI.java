package com.sundaempire.frontend.ui;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UI {

    private UIState currentState;
    private final InputMultiplexer multiplexer;

    private final SpriteBatch batch;
    private final BitmapFont font;

    public UI(InputMultiplexer multiplexer) {
        this.multiplexer = multiplexer;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();

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

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
