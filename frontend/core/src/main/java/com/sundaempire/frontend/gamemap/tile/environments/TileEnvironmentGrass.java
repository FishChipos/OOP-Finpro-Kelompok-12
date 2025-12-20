package com.sundaempire.frontend.gamemap.tile.environments;

import com.badlogic.gdx.graphics.Texture;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemap.tile.TileEnvironment;

public class TileEnvironmentGrass extends TileEnvironment {
    public TileEnvironmentGrass() {
        Texture texture = GameManager.INSTANCE.getAssetManager().get("textures/tiles/grass.png");
        super.setTexture(texture);
    }
}
