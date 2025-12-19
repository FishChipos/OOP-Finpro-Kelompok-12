package com.sundaempire.frontend.settlement;

import com.sundaempire.frontend.settlement.buildings.BuildingFarm;
import com.sundaempire.frontend.settlement.buildings.BuildingGuardTower;

public class BuildingFactory {

    public static Building createBuilding(String type) {
        switch (type) {
            case "FARM":
                return new BuildingFarm();
            case "GUARD_TOWER":
                return new BuildingGuardTower();
            default:
                throw new IllegalArgumentException("Unknown building type: " + type);
        }
    }
}
