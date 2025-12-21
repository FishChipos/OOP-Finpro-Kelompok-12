package com.sundaempire.frontend.ui.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sundaempire.frontend.gamemanager.GameActor;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemap.tile.Tile;
import com.sundaempire.frontend.settlement.Settlement;
import com.sundaempire.frontend.ui.UI;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.commands.UnitCommandMoveLeft;
import com.sundaempire.frontend.unit.commands.UnitCommandMoveRight;
import com.sundaempire.frontend.unit.commands.UnitCommandMoveUp;

import java.util.Set;

public class UIStateGame extends UIState {

    private InputAdapter inputProcessor;

    Label roundCounter;
    Label goldCounter;
    Label tileNameLabel;
    Label settlementLabel;

    public UIStateGame(UI ui) {
        this.ui = ui;
    }

    @Override
    public void onEnter() {
        Table table = ui.getTable();
        Skin skin = ui.getSkin();

        Pixmap backgroundPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        backgroundPixmap.setColor(0f, 0f, 0f, 0.75f);
        backgroundPixmap.fill();
        Texture backgroundTexture = new Texture(backgroundPixmap, Pixmap.Format.RGBA8888, false);

        Table gameViewportTable = new Table();
        gameViewportTable.background(new TextureRegionDrawable(backgroundTexture));

        roundCounter = new Label("", skin);
        updateRoundCounter();
        goldCounter = new Label("", skin);
        updateGoldCounter();
        Label currentlyMoving = new Label("PLAYER 1", skin);
        Image goldIcon = new Image(GameManager.INSTANCE.getAssetManager().get("textures/gold.png", Texture.class));

        gameViewportTable.top().pad(20f);
        gameViewportTable.row().expandY().top().space(20f);
        gameViewportTable.add(roundCounter).width(10f);
        gameViewportTable.add(currentlyMoving).expandX();
        gameViewportTable.add(goldCounter).width(10f);
        gameViewportTable.add(goldIcon).width(20f);

        Table sidepanelTable = new Table();

        TextButton upButton = new TextButton("UP", skin);
        upButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Unit unit = GameManager.INSTANCE.getRoundManager().getActiveUnit();

                if (unit != null) {
                    unit.setNextCommand(new UnitCommandMoveUp());
                }
            }
        });

        TextButton leftButton = new TextButton("LEFT", skin);
        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Unit unit = GameManager.INSTANCE.getRoundManager().getActiveUnit();

                if (unit != null) {
                    unit.setNextCommand(new UnitCommandMoveLeft());
                }
            }
        });

        TextButton middleButton = new TextButton("", skin);

        TextButton rightButton = new TextButton("RIGHT", skin);
        rightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Unit unit = GameManager.INSTANCE.getRoundManager().getActiveUnit();

                if (unit != null) {
                    unit.setNextCommand(new UnitCommandMoveRight());
                }
            }
        });

        TextButton downButton = new TextButton("DOWN", skin);
        downButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Unit unit = GameManager.INSTANCE.getRoundManager().getActiveUnit();

                if (unit != null) {
                    unit.setNextCommand(new UnitCommandMoveRight());
                }
            }
        });

        tileNameLabel = new Label("", skin);
        tileNameLabel.setFontScale(1.5f);
        settlementLabel = new Label("", skin);
        settlementLabel.setFontScale(2f);

        // Set background for the side panel.

        sidepanelTable.background(new TextureRegionDrawable(backgroundTexture));
        backgroundPixmap.dispose();

        sidepanelTable.top().pad(20f);
        sidepanelTable.defaults().width(40f).height(40f);
        sidepanelTable.row();
        sidepanelTable.add(upButton).colspan(3);
        sidepanelTable.row();
        sidepanelTable.add(leftButton);
        sidepanelTable.add(middleButton);
        sidepanelTable.add(rightButton);
        sidepanelTable.row();
        sidepanelTable.add(downButton).colspan(3);
        sidepanelTable.defaults().reset();
        sidepanelTable.defaults().colspan(3).space(10f);
        sidepanelTable.row();
        sidepanelTable.add(tileNameLabel);
        sidepanelTable.add(settlementLabel);

        table.row();
        table.add(gameViewportTable).fillX().expandX().left().top();
        table.add(sidepanelTable).width(150f).fill().expandY().left().top();
    }

    @Override
    public void update(float deltaTime) {
        ui.getGameMap().update(deltaTime);

        for (Settlement settlement : ui.getSettlements()) {
            settlement.update(deltaTime);
        }

        for (Unit unit : ui.getUnits()) {
            unit.update(deltaTime);
        }
    }

    @Override
    public void render(Batch batch) {
        ui.getGameMap().render(batch);

        for (Settlement settlement : ui.getSettlements()) {
            settlement.render(batch);
        }

        for (Unit unit : ui.getUnits()) {
            unit.render(batch);
        }
    }

    @Override
    public void registerInputProcessor(InputMultiplexer multiplexer) {
        inputProcessor = new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Unit unit = GameManager.INSTANCE.getRoundManager().getActiveUnit();

                if (button == Input.Buttons.RIGHT && unit != null) {
                    Vector3 worldPos = ui.getCamera().unproject(new Vector3(screenX, screenY, 0));
                    unit.getUnitState().setNextCommand(new UnitCommandMoveRight());
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

    @Override
    public void notice() {
        updateRoundCounter();
        updateGoldCounter();
        updateTileInfo();
        updateSettlementInfo();
    }

    private void updateRoundCounter() {
        roundCounter.setText("ROUND " + GameManager.INSTANCE.getRoundManager().getRound());
    }

    private void updateGoldCounter() {
        GameActor currentGameActor = GameManager.INSTANCE.getRoundManager().getCurrentGameActor();
        goldCounter.setText(GameManager.INSTANCE.getPlayerManager().getPlayer(currentGameActor).getGold());
    }

    private void updateTileInfo() {
        Tile selectedTile = UI.INSTANCE.getGameMap().getSelectedTile();

        if (selectedTile == null) {
            return;
        }

        tileNameLabel.setText(selectedTile.getEnvironment().getName());
    }

    private void updateSettlementInfo() {
        Tile selectedTile = UI.INSTANCE.getGameMap().getSelectedTile();

        if (selectedTile == null) {
            settlementLabel.setText("");
            return;
        }

        Settlement settlement = GameManager.INSTANCE.getPlayerManager().getSettlement(selectedTile.getCoordinates());

        if (settlement == null) {
            settlementLabel.setText("");
        }
        else {
            settlementLabel.setText("Settlement");
        }
    }
}
