package com.sundaempire.frontend.gamemap.tile.environments;

import com.badlogic.gdx.graphics.Texture;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemap.tile.TileEnvironment;

public class TileEnvironmentWater extends TileEnvironment {
    public TileEnvironmentWater() {
        Texture texture = GameManager.INSTANCE.getAssetManager().get("textures/tiles/water.png");
        super.setTexture(texture);
    }
}
