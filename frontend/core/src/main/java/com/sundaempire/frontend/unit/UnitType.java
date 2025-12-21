package com.sundaempire.frontend.unit;

import com.badlogic.gdx.graphics.Texture;

public enum UnitType {
    EXPLORER(
        new UnitStats("Explorer", 2, 1, 2, 1, 2),
        new UnitAssets("textures/units/explorer.png", "textures/units/explorer_outline.png")
    ),
    SWORDSMAN(
        new UnitStats("Swordsman", 6, 2, 1, 1, 3),
        new UnitAssets("textures/units/swordsman.png", "textures/units/swordsman_outline.png")
    ),
    ARCHER(
        new UnitStats("Archer", 3, 2, 1, 2, 3),
        new UnitAssets("textures/units/archer.png", "textures/units/archer_outline.png")
    );

    private final UnitStats unitStats;
    private final UnitAssets unitAssets;

    UnitType(UnitStats unitStats, UnitAssets unitAssets) {
        this.unitStats = unitStats;
        this.unitAssets = unitAssets;
    }

    public UnitStats getUnitStats() {
        return unitStats;
    }

    public UnitAssets getUnitAssets() {
        return unitAssets;
    }
}
