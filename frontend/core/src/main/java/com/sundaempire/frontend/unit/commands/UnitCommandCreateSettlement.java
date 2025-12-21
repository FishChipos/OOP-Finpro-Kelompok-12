package com.sundaempire.frontend.unit.commands;

import com.badlogic.gdx.Game;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemanager.RoundManager;
import com.sundaempire.frontend.settlement.SettlementFactory;
import com.sundaempire.frontend.ui.UI;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.UnitPool;
import com.sundaempire.frontend.unit.UnitType;
import com.sundaempire.frontend.unit.states.UnitStateIdle;

public class UnitCommandCreateSettlement extends UnitCommand {
    @Override
    public boolean execute(Unit unit) {
        if (unit.getUnitType() == UnitType.EXPLORER) {
            UI.INSTANCE.getSettlements().add(SettlementFactory.createInitialSettlement(unit.getCoordinates(), unit.getGameMap()));
            unit.changeUnitState(new UnitStateIdle(unit));
            GameManager.INSTANCE.getRoundManager().nextUnit();
            return true;
        }

        return false;
    }
}
