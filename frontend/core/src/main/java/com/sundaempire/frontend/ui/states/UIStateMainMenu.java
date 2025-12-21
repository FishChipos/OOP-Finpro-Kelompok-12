package com.sundaempire.frontend.ui.states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.sundaempire.frontend.ui.UI;

public class UIStateMainMenu extends UIState {

    private InputAdapter inputProcessor;

    public UIStateMainMenu(UI ui) {
        this.ui = ui;
    }

    @Override
    public void onEnter() {
        Table table = ui.getTable();
        Skin skin = ui.getSkin();

        Label title = new Label("SUNDA EMPIRE", skin);
        title.setFontScale(3f);
        Label startPrompt = new Label("Press ENTER to start", skin);
        Label exitPrompt = new Label("and ESC to exit", skin);

        table.defaults().left().expandX().padLeft(20f).space(10f);
        table.row();
        table.add(title);
        table.row();
        table.add(startPrompt);
        table.row();
        table.add(exitPrompt);
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public void registerInputProcessor(InputMultiplexer multiplexer) {
        inputProcessor = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.ENTER) {
                    ui.setState(new UIStateGame(ui));
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
