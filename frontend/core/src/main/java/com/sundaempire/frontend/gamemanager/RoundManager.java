package com.sundaempire.frontend.gamemanager;

import com.sundaempire.frontend.Notifiable;
import com.sundaempire.frontend.Observable;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.states.UnitStateIdle;
import com.sundaempire.frontend.unit.states.UnitStateMoving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundManager implements Observable {
    private int round = 1;
    private GameActor currentGameActor = GameActor.PLAYER_1;
    private Map<GameActor, List<Unit>> gameActorUnits = new HashMap<>();
    private int gameActorUnitIndex = -1;

    private List<Notifiable> roundChangeObservers = new ArrayList<>();

    public RoundManager() {
        for (GameActor gameActor : GameActor.values()) {
            gameActorUnits.put(gameActor, new ArrayList<>());
        }
    }

    public void nextRound() {
        ++round;
        currentGameActor = GameActor.PLAYER_1;

        for (Notifiable observer : roundChangeObservers) {
            observer.notice();
        }
    }

    public void nextTurn() {
        gameActorUnitIndex = 0;

        if (currentGameActor == GameActor.PLAYER_1) {
            nextRound();
            return;
        }

        currentGameActor = currentGameActor.nextGameActor();
    }

    public void nextUnit() {
        Unit unit;

        if (gameActorUnitIndex >= 0) {
            unit = gameActorUnits.get(currentGameActor).get(gameActorUnitIndex);
            unit.changeUnitState(new UnitStateIdle(unit));
        }

        ++gameActorUnitIndex;

        if (gameActorUnitIndex >= gameActorUnits.get(currentGameActor).size()) {
            nextTurn();
        }

        unit = gameActorUnits.get(currentGameActor).get(gameActorUnitIndex);
        unit.changeUnitState(new UnitStateMoving(unit));
    }

    @Override
    public void addObserver(Notifiable observer) {
        roundChangeObservers.add(observer);
    }

    @Override
    public void removeObserver(Notifiable observer) {
        roundChangeObservers.remove(observer);
    }

    public void addUnit(Unit unit) {
        gameActorUnits.get(unit.getOwner()).add(unit);
    }

    public void removeUnit(Unit unit) {
        gameActorUnits.get(unit.getOwner()).remove(unit);
    }

    public Unit getActiveUnit() {
        try {
            return gameActorUnits.get(currentGameActor).get(gameActorUnitIndex);
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public int getRound() {
        return round;
    }
}
