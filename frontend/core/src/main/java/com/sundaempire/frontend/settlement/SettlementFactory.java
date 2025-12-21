package com.sundaempire.frontend.settlement;

import com.sundaempire.frontend.faction.Faction;

public class SettlementFactory {

    public static Settlement createInitialSettlement() {
        Settlement settlement = new Settlement(Faction.PLAYER);

        // default starting buildings
        settlement.addBuilding(BuildingFactory.createBuilding("FARM"));

        return settlement;
    }
}
