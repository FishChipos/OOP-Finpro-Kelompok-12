package com.sundaempire.frontend.turn;

import com.sundaempire.frontend.faction.Faction;

public class TurnManager {

    private int turn = 1;
    private Faction activeFaction = Faction.PLAYER;

    public void nextTurn() {
        if (activeFaction == Faction.PLAYER) {
            activeFaction = Faction.ENEMY;
        } else {
            activeFaction = Faction.PLAYER;
            turn++;
        }
    }

    public int getTurn() {
        return turn;
    }

    public Faction getActiveFaction() {
        return activeFaction;
    }
}
