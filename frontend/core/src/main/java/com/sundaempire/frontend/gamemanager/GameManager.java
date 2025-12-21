package com.sundaempire.frontend.gamemanager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public enum GameManager {
    INSTANCE;

    private final AssetManager assetManager;
    private final RoundManager roundManager;

    private GameManager() {
        assetManager = new AssetManager();
        roundManager = new RoundManager();
    }

    public void loadAssets() {
        assetManager.load("textures/tiles/grass.png", Texture.class);
        assetManager.load("textures/tiles/water.png", Texture.class);
        assetManager.load("textures/tiles/mountain.png", Texture.class);

        assetManager.load("textures/units/explorer.png", Texture.class);
        assetManager.load("textures/units/swordsman.png", Texture.class);
        assetManager.load("textures/units/archer.png", Texture.class);

        assetManager.load("textures/buildings/farm.png", Texture.class);
        assetManager.load("textures/buildings/guard_tower.png", Texture.class);
    }

    public void finishLoading() {
        assetManager.finishLoading();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public RoundManager getRoundManager() {
        return roundManager;
    }
}
