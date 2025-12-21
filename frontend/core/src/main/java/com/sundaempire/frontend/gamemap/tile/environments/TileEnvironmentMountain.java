package com.sundaempire.frontend.gamemap.tile.environments;

import com.badlogic.gdx.graphics.Texture;
import com.sundaempire.frontend.gamemanager.GameManager;
import com.sundaempire.frontend.gamemap.tile.TileEnvironment;

public class TileEnvironmentMountain extends TileEnvironment {
    public TileEnvironmentMountain() {
        Texture texture = GameManager.INSTANCE.getAssetManager().get("textures/tiles/mountain.png");
        setTexture(texture);
        setName("Mountain");
    }
}
