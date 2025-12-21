package com.sundaempire.frontend.settlement;

import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.gamemap.GameMap;

public class SettlementFactory {
    public static Settlement createInitialSettlement(Vector2 coordinates, GameMap gameMap) {
        Settlement settlement = new Settlement();
        settlement.setCoordinates(coordinates);
        settlement.setGameMap(gameMap);
        settlement.configure();

        // default starting buildings
        settlement.addBuilding(BuildingFactory.createBuilding("FARM"));

        return settlement;
    }
}
