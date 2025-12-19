package com.sundaempire.frontend.ui.states;

import com.sundaempire.frontend.ui.UIState;
import com.sundaempire.frontend.unit.Unit;

public class UIGameState implements UIState {
    private Unit selectedUnit;

    @Override
    public void onEnter() {
        selectedUnit = null;
    }

    @Override
    public void onExit() {
        selectedUnit = null;
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void render() {
    }

    @Override
    public void handleInput() {
    }

    public void selectUnit(Unit unit) {
        if (selectedUnit != null) {
            selectedUnit.deselect();
        }
        selectedUnit = unit;
        if (selectedUnit != null) {
            selectedUnit.select();
        }
    }

    public void commandMove(float x, float y) {
        if (selectedUnit != null) {
            selectedUnit.moveTo(x, y);
        }
    }
}