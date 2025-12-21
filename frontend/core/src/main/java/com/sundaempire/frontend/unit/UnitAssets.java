package com.sundaempire.frontend.unit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.sundaempire.frontend.gamemanager.GameManager;

public class UnitAssets {
    private String texturePath;
    private String outlineTexturePath;

    private Texture texture;
    private Texture outlineTexture;

    public UnitAssets() {

    }

    public UnitAssets(String texturePath, String outlineTexturePath) {
        setTexturePath(texturePath);
        setOutlineTexturePath(outlineTexturePath);
    }

    public void set(UnitAssets unitAssets) {
        setTexturePath(unitAssets.getTexturePath());
        setOutlineTexturePath(unitAssets.getOutlineTexturePath());
    }

    public String getTexturePath() {
        return texturePath;
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
        texture = GameManager.INSTANCE.getAssetManager().get(this.texturePath);
    }

    public String getOutlineTexturePath() {
        return outlineTexturePath;
    }

    public void setOutlineTexturePath(String outlineTexturePath) {
        this.outlineTexturePath = outlineTexturePath;
        outlineTexture = GameManager.INSTANCE.getAssetManager().get(this.outlineTexturePath);
    }

    public Texture getTexture() {
        return texture;
    }

    public Texture getOutlineTexture() {
        return outlineTexture;
    }
}
