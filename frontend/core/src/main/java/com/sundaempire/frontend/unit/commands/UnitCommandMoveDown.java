package com.sundaempire.frontend.unit.commands;

public class UnitCommandMoveDown extends UnitCommandMove {
    public UnitCommandMoveDown() {
        super();
        this.coordinateTranslation.set(0f, -1f);
    }
}
