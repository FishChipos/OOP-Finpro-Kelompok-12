package com.sundaempire.frontend.settlement.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.settlement.Building;
import com.sundaempire.frontend.settlement.Settlement;

public class BuildingGuardTower extends Building {

    public BuildingGuardTower() {
        this.name = "Guard Tower";
        this.cost = 100;
        this.texture = GameManager.INSTANCE
            .getAssetManager()
            .get("textures/buildings/guard_tower.png", Texture.class);
    }

    @Override
    public void applyEffect(Settlement settlement) {
        settlement.addDefense(5);
    }
}
