package com.sundaempire.frontend.unit.states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.commands.*;

import java.util.HashMap;
import java.util.Map;

public class UnitStateMoving extends UnitState {
    private final Map<Integer, UnitCommand> keyCommands = new HashMap<>();

    private InputProcessor inputProcessor = new InputAdapter() {
        @Override
        public boolean keyDown(int keycode) {
            if (keyCommands.containsKey(keycode)) {

                UnitStateMoving.this.nextCommand = UnitStateMoving.this.keyCommands.get(keycode);
                return true;
            }
            else {
                return false;
            }
        }
    };

    public UnitStateMoving(Unit unit) {
        this.unit = unit;

        keyCommands.put(Input.Keys.W, new UnitCommandMoveUp());
        keyCommands.put(Input.Keys.S, new UnitCommandMoveDown());
        keyCommands.put(Input.Keys.D, new UnitCommandMoveRight());
        keyCommands.put(Input.Keys.A, new UnitCommandMoveLeft());
        keyCommands.put(Input.Keys.E, new UnitCommandCreateSettlement());
    }

    @Override
    public void enter() {
        unit.setMovement(unit.getUnitStats().getMaxMovement());
    }

    @Override
    public void update(float delta) {
        if (nextCommand != null) {

            if (nextCommand.execute(unit) && unit.getMovement() <= 0) {
                GameManager.INSTANCE.getRoundManager().nextUnit();
            }

            nextCommand = null;
        }
    }

    @Override
    public void render(Batch batch) {
        super.render(batch);
        drawActiveOutline(batch);
    }

    private void drawActiveOutline(Batch batch) {
        Rectangle collider = unit.getCollider();
        batch.setColor(0.92f, 0.84f, 0.02f, 1f);
        batch.draw(unit.getUnitAssets().getOutlineTexture(), collider.x, collider.y, collider.width, collider.height);
        batch.setColor(1f, 1f, 1f, 1f);
    }

    @Override
    public void registerInputProcessor(InputMultiplexer inputMultiplexer) {
        inputMultiplexer.addProcessor(inputProcessor);
    }

    @Override
    public void deregisterInputProcessor(InputMultiplexer inputMultiplexer) {
        inputMultiplexer.removeProcessor(inputProcessor);
    }
}
