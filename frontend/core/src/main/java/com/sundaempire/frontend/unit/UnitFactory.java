package com.sundaempire.frontend.unit;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.gamemanager.GameActor;
import com.sundaempire.frontend.gamemap.GameMap;

public class UnitFactory {
    public Unit createUnit() {
        Unit unit = new Unit();
        return unit;
    }

    public Unit configureUnit(Vector2 position, Unit unit, UnitType unitType, GameActor owner, GameMap gameMap, InputMultiplexer inputMultiplexer) {
        unit.setPosition(position);
        unit.setUnitType(unitType);
        unit.setOwner(owner);
        unit.setGameMap(gameMap);
        unit.setInputMultiplexer(inputMultiplexer);
        unit.configure();

        return unit;
    }
}
