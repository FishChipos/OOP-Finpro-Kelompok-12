package com.sundaempire.frontend.unit.states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.UnitCommand;
import com.sundaempire.frontend.unit.UnitState;
import com.sundaempire.frontend.unit.commands.UnitCommandMoveDown;
import com.sundaempire.frontend.unit.commands.UnitCommandMoveLeft;
import com.sundaempire.frontend.unit.commands.UnitCommandMoveRight;
import com.sundaempire.frontend.unit.commands.UnitCommandMoveUp;

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
    }

    @Override
    public void enter() {
        unit.setMovement(unit.getUnitStats().getMaxMovement());
    }

    @Override
    public void update(float delta) {
        if (nextCommand != null) {

            if (nextCommand.execute(unit)) {
                GameManager.INSTANCE.getRoundManager().nextUnit();
            }

            nextCommand = null;
        }
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
