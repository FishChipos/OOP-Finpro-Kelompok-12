package com.sundaempire.frontend.settlement;

public class SettlementFactory {

    public static Settlement createInitialSettlement() {
        Settlement settlement = new Settlement();

        // default starting buildings
        settlement.addBuilding(BuildingFactory.createBuilding("FARM"));

        return settlement;
    }
}
