package com.sundaempire.frontend.settlement.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.settlement.Building;
import com.sundaempire.frontend.settlement.Settlement;

public class BuildingFarm extends Building {

    public BuildingFarm() {
        this.name = "Farm";
        this.cost = 50;
        this.texture = GameManager.INSTANCE
            .getAssetManager()
            .get("textures/buildings/farm.png", Texture.class);
    }

    @Override
    public void applyEffect(Settlement settlement) {
        settlement.addFood(10);
    }
}
