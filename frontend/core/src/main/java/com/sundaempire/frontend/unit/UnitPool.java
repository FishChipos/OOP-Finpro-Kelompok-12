package com.sundaempire.frontend.unit;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;

import com.sundaempire.frontend.gamemanager.GameActor;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemap.GameMap;
import com.sundaempire.frontend.player.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public enum UnitPool {
    INSTANCE;

    private static final int POOL_CAPACITY = 50;
    private List<Unit> inactiveUnits = new ArrayList<>(POOL_CAPACITY);
    private List<Unit> activeUnits = new ArrayList<>(POOL_CAPACITY);

    private int unitCount = 0;

    private GameMap gameMap;
    private InputMultiplexer inputMultiplexer;

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setInputMultiplexer(InputMultiplexer inputMultiplexer) {
        this.inputMultiplexer = inputMultiplexer;
    }

    public Unit obtain(Vector2 coordinates, UnitType unitType, GameActor owner) {
        Unit unit;

        if (unitCount < POOL_CAPACITY) {
            unit = UnitFactory.createUnit();
            ++unitCount;
        }
        else if (!inactiveUnits.isEmpty()) {
            unit = inactiveUnits.remove(inactiveUnits.size() - 1);
        }
        else {
            return null;
        }

        unitFactory.configureUnit(position, unit, unitType, owner, gameMap, inputMultiplexer);
        PlayerManager player = GameManager.INSTANCE.getPlayerManagers()
            .stream()
            .filter(p -> p.getActor() == owner)
            .findFirst()
            .orElse(null);
        if (player != null) player.addUnit(unit);

        activeUnits.add(unit);

        return unit;
    }

    public void release(Unit unit) {
        unit.dispose();
        inactiveUnits.add(unit);
        activeUnits.remove(unit);
        PlayerManager player = GameManager.INSTANCE.getPlayerManagers()
            .stream()
            .filter(p -> p.getActor() == unit.getOwner())
            .findFirst()
            .orElse(null);
        if (player != null) player.removeUnit(unit);
    }

    public List<Unit> getActiveUnits() {
        return activeUnits;
    }

    public void releaseAll() {
        List<Unit> copy = new ArrayList<>(getActiveUnits());
        for (Unit unit : copy) {
            release(unit);
        }

        activeUnits.clear();
    }
}
