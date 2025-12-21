package com.sundaempire.frontend.gamemanager;

import com.badlogic.gdx.Game;
import com.sundaempire.frontend.Notifiable;
import com.sundaempire.frontend.Observable;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.states.UnitStateIdle;
import com.sundaempire.frontend.unit.states.UnitStateMoving;
import com.sundaempire.frontend.player.PlayerManager;

import java.util.List;

public class RoundManager {
    private int round = 0;
    private GameActor currentGameActor = GameActor.PLAYER_1;
    private int gameActorUnitIndex = 0;

    private final List<PlayerManager> players;

    public RoundManager(List<PlayerManager> players) {
        this.players = players;
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
        PlayerManager activePlayer = getCurrentPlayer();
        List<Unit> units = activePlayer.getUnits();
        if (units.isEmpty()) return;

        Unit unit = units.get(gameActorUnitIndex);
        unit.changeUnitState(new UnitStateIdle(unit));
        ++gameActorUnitIndex;

        if (gameActorUnitIndex >= units.size()) {
            nextTurn();
        }

        unit = units.get(gameActorUnitIndex % units.size()); // safety
        unit.changeUnitState(new UnitStateMoving(unit));
    }

    public PlayerManager getCurrentPlayer() {
        for (PlayerManager pm : players) {
            if (pm.getActor() == currentGameActor) return pm;
        }
        return null;
    }

    public Unit getActiveUnit() {
        PlayerManager activePlayer = getCurrentPlayer();
        try {
            return activePlayer.getUnits().get(gameActorUnitIndex);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
