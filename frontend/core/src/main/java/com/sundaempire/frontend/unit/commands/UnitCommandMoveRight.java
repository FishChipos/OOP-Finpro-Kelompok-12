package com.sundaempire.frontend.unit.commands;

import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.UnitCommand;

public class UnitCommandMoveRight extends UnitCommandMove {
    public UnitCommandMoveRight() {
        this.coordinateTranslation.set(1f, 0f);
    }
}
