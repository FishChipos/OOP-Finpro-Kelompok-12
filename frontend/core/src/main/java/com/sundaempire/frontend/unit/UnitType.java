package com.sundaempire.frontend.unit;

import com.badlogic.gdx.graphics.Texture;

public enum UnitType {
    EXPLORER(
        new UnitStats("Explorer", 2, 1, 2, 1, 2),
        "textures/units/explorer.png"
    ),
    SWORDSMAN(
        new UnitStats("Swordsman", 6, 2, 1, 1, 3),
        "textures/units/swordsman.png"
    ),
    ARCHER(
        new UnitStats("Archer", 3, 2, 1, 2, 3),
        "textures/units/archer.png"
    );

    private final UnitStats unitStats;
    private final String texturePath;

    UnitType(UnitStats unitStats, String texturePath) {
        this.unitStats = unitStats;
        this.texturePath = texturePath;
    }

    public UnitStats getUnitStats() {
        return unitStats;
    }

    public String getTexturePath() {
        return texturePath;
    }
}
