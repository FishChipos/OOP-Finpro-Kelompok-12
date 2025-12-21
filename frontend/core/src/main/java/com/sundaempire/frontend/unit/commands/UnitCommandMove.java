package com.sundaempire.frontend.unit.commands;

import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.gamemap.tile.Tile;
import com.sundaempire.frontend.gamemap.tile.environments.TileEnvironmentGrass;
import com.sundaempire.frontend.unit.Unit;

public abstract class UnitCommandMove extends UnitCommand {
    protected Vector2 coordinateTranslation = new Vector2();

    @Override
    public boolean execute(Unit unit) {
        if (canMove(unit)) {
            unit.translate(coordinateTranslation);
            unit.decrementMovement();
            return true;
        }

        return false;
    }

    public boolean canMove(Unit unit) {
        Vector2 newCoordinates = new Vector2(unit.getCoordinates());
        newCoordinates.add(coordinateTranslation);
        boolean isCoordinateInBounds = unit.getGameMap().isCoordinateInBounds(newCoordinates);

        if (isCoordinateInBounds) {
            Tile targetTile = unit.getGameMap().getTile(newCoordinates);
            if (targetTile.getEnvironment() instanceof TileEnvironmentGrass) {
                return unit.getGameMap().isCoordinateInBounds(newCoordinates);
            }
        }



        return false;
    }
}
