package com.sundaempire.frontend.player;

import com.sundaempire.frontend.gamemanager.GameActor;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.settlement.Settlement;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {

    private final GameActor actor;

    private int gold = 100;
    private int food = 50;

    private final List<Unit> units = new ArrayList<>();
    private final List<Settlement> settlements = new ArrayList<>();

    public PlayerManager(GameActor actor) {
        this.actor = actor;
    }

    public boolean canBuildSettlement(int cost) {
        return gold >= cost;
    }

    public void spendGold(int amount) {
        gold -= amount;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public int getGold() {
        return gold;
    }

    public void addFood(int amount) { food += amount; }
    public int getFood() { return food; }

    // Units
    public void addUnit(Unit unit) { units.add(unit); }
    public void removeUnit(Unit unit) { units.remove(unit); }
    public List<Unit> getUnits() { return units; }

    // Settlements
    public void addSettlement(Settlement settlement) { settlements.add(settlement); }
    public List<Settlement> getSettlements() { return settlements; }

    public GameActor getActor() { return actor; }
}
