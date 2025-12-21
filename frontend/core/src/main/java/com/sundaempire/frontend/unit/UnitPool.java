package com.sundaempire.frontend.unit;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;

import com.sundaempire.frontend.gamemanager.GameActor;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemap.GameMap;

import java.util.ArrayList;
import java.util.List;

public class UnitPool {
    private static final int POOL_CAPACITY = 50;
    private List<Unit> inactiveUnits = new ArrayList<>(POOL_CAPACITY);
    private List<Unit> activeUnits = new ArrayList<>(POOL_CAPACITY);

    private int unitCount = 0;

    private UnitFactory unitFactory;
    private GameMap gameMap;
    private InputMultiplexer inputMultiplexer;

    public UnitPool(UnitFactory unitFactory, GameMap gameMap, InputMultiplexer inputMultiplexer) {
        this.unitFactory = unitFactory;
        this.gameMap = gameMap;
        this.inputMultiplexer = inputMultiplexer;
    }

    public Unit obtain(Vector2 position, UnitType unitType, GameActor owner) {
        Unit unit;

        if (unitCount < POOL_CAPACITY) {
            unit = unitFactory.createUnit();
            ++unitCount;
        }
        else if (!inactiveUnits.isEmpty()) {
            unit = inactiveUnits.remove(inactiveUnits.size() - 1);
        }
        else {
            return null;
        }

        unitFactory.configureUnit(position, unit, unitType, owner, gameMap, inputMultiplexer);
        GameManager.INSTANCE.getRoundManager().addUnit(unit);

        activeUnits.add(unit);

        return unit;
    }

    public void release(Unit unit) {
        inactiveUnits.add(unit);
        activeUnits.remove(unit);
        GameManager.INSTANCE.getRoundManager().removeUnit(unit);
    }

    public List<Unit> getActiveUnits() {
        return activeUnits;
    }

    public void releaseAll() {
        for (Unit unit : getActiveUnits()) {
            release(unit);
        }
    }
}
