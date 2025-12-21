package com.sundaempire.frontend.unit.commands;

import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.UnitCommand;

public abstract class UnitCommandMove implements UnitCommand {
    protected Vector2 coordinateTranslation = new Vector2();

    @Override
    public boolean execute(Unit unit) {
        if (canMove(unit)) {
            unit.translate(coordinateTranslation);
            return true;
        }

        return false;
    }

    public boolean canMove(Unit unit) {
        Vector2 newCoordinate = new Vector2(unit.getCoordinates());
        newCoordinate.add(coordinateTranslation);

        return unit.getGameMap().isCoordinateInBounds(newCoordinate);
    }
}
