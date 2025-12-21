package com.sundaempire.frontend.unit.commands;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemanager.RoundManager;
import com.sundaempire.frontend.settlement.Settlement;
import com.sundaempire.frontend.settlement.SettlementFactory;
import com.sundaempire.frontend.ui.UI;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.UnitPool;
import com.sundaempire.frontend.unit.UnitType;
import com.sundaempire.frontend.unit.states.UnitStateIdle;

import java.util.List;
import java.util.Vector;

public class UnitCommandCreateSettlement extends UnitCommand {
    @Override
    public boolean execute(Unit unit) {
        if (unit.getUnitType() == UnitType.EXPLORER) {
            Vector2 unitCoordinates = unit.getCoordinates();

            if (GameManager.INSTANCE.getPlayerManager().getSettlement(unitCoordinates) != null) {
                return false;
            }

            Settlement settlement = SettlementFactory.createInitialSettlement(unitCoordinates, unit.getGameMap());
            GameManager.INSTANCE.getPlayerManager().getPlayer(unit.getOwner()).getSettlements().add(settlement);
            UI.INSTANCE.getSettlements().add(settlement);

            unit.changeUnitState(new UnitStateIdle(unit));
            GameManager.INSTANCE.getRoundManager().nextUnit();

            GameManager.INSTANCE.getPlayerManager().getPlayer(unit.getOwner()).spendGold(50);
            return true;
        }

        return false;
    }
}
