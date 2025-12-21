package com.sundaempire.frontend.gamemanager;

import com.badlogic.gdx.Game;
import com.sundaempire.frontend.Notifiable;
import com.sundaempire.frontend.Observable;
import com.sundaempire.frontend.player.Player;
import com.sundaempire.frontend.player.PlayerManager;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.states.UnitStateIdle;
import com.sundaempire.frontend.unit.states.UnitStateMoving;

import java.util.*;

import static com.sundaempire.frontend.gamemanager.GameActor.PLAYER_1;

public class RoundManager implements Observable {
    private int round = 1;
    private GameActor currentGameActor = PLAYER_1;
    private int currentUnitIndex = -1;
    private PlayerManager playerManager;
    private Unit currentUnit;
    private List<Unit> toBeAdded = new ArrayList<>();
    private List<Unit> toBeRemoved = new ArrayList<>();

    private List<Notifiable> roundChangeObservers = new ArrayList<>();

    public RoundManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public void nextRound() {
        ++round;
        currentGameActor = PLAYER_1;

        for (Notifiable observer : roundChangeObservers) {
            observer.notice();
        }
    }

    public void nextTurn() {
        updateUnitList();
        currentUnitIndex = 0;

        if (currentGameActor == PLAYER_1) {
            nextRound();
            return;
        }

        currentGameActor = currentGameActor.nextGameActor();
    }

    public void nextUnit() {
        if (currentUnit != null) {
            currentUnit.changeUnitState(new UnitStateIdle(currentUnit));
        }

        ++currentUnitIndex;

        if (currentUnitIndex >= playerManager.getPlayer(currentGameActor).getUnits().size()) {
            nextTurn();
        }

        currentUnit = playerManager.getPlayer(currentGameActor).getUnits().get(currentUnitIndex);
        currentUnit.changeUnitState(new UnitStateMoving(currentUnit));
    }

    @Override
    public void addObserver(Notifiable observer) {
        roundChangeObservers.add(observer);
    }

    @Override
    public void removeObserver(Notifiable observer) {
        roundChangeObservers.remove(observer);
    }

    public void updateUnitList() {
        for (Unit unit : toBeRemoved) {
            playerManager.getPlayer(unit.getOwner()).getUnits().remove(unit);
        }

        toBeRemoved.clear();

        for (Unit unit : toBeAdded) {
            playerManager.getPlayer(unit.getOwner()).getUnits().add(unit);
        }

        toBeAdded.clear();
    }

    public void addUnit(Unit unit) {
        toBeAdded.add(unit);
    }

    public void removeUnit(Unit unit) {
        toBeRemoved.add(unit);
    }

    public Unit getActiveUnit() {
        return currentUnit;
    }

    public int getRound() {
        return round;
    }
}
