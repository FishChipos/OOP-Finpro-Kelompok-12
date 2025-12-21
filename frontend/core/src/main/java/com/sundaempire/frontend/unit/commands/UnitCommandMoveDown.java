package com.sundaempire.frontend.unit.commands;

import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.UnitCommand;

public class UnitCommandMoveDown extends UnitCommandMove {
    public UnitCommandMoveDown() {
        super();
        this.coordinateTranslation.set(0f, -1f);
    }
}
