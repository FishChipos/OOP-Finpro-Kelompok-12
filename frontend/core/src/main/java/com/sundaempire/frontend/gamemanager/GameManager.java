package com.sundaempire.frontend.gamemanager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public enum GameManager {
    INSTANCE;

    private final AssetManager assetManager;

    private GameManager() {
        assetManager = new AssetManager();
    }

    public void loadAssets() {
        assetManager.load("textures/tiles/grass.png", Texture.class);
        assetManager.load("textures/tiles/water.png", Texture.class);
        assetManager.load("textures/tiles/mountain.png", Texture.class);

        assetManager.load("textures/buildings/farm.png", Texture.class);
        assetManager.load("textures/buildings/guard_tower.png", Texture.class);
    }

    public void finishLoading() {
        assetManager.finishLoading();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
