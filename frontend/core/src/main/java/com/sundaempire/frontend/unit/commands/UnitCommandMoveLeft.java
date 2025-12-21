package com.sundaempire.frontend.unit.commands;

public class UnitCommandMoveLeft extends UnitCommandMove {
    public UnitCommandMoveLeft() {
        this.coordinateTranslation.set(-1f, 0f);
    }
}
