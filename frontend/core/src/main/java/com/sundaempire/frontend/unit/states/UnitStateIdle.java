package com.sundaempire.frontend.unit.states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.UnitState;
import com.sundaempire.frontend.unit.commands.UnitCommandMoveDown;
import com.sundaempire.frontend.unit.commands.UnitCommandMoveLeft;
import com.sundaempire.frontend.unit.commands.UnitCommandMoveRight;
import com.sundaempire.frontend.unit.commands.UnitCommandMoveUp;

public class UnitStateIdle extends UnitState {
    public UnitStateIdle(Unit unit) {
        this.unit = unit;
    }
}
