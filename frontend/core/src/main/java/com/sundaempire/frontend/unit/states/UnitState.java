package com.sundaempire.frontend.unit.states;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.sundaempire.frontend.unit.Unit;
import com.sundaempire.frontend.unit.commands.UnitCommand;

public abstract class UnitState {
    protected Unit unit;
    protected UnitCommand nextCommand;

    public UnitCommand getNextCommand() {
        return nextCommand;
    }

    public void setNextCommand(UnitCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    public void enter() {

    }

    public void update(float delta) {

    };

    public void render(Batch batch) {
        batch.draw(unit.getUnitAssets().getTexture(), unit.getCollider().getX(), unit.getCollider().getY(), unit.getCollider().getWidth(), unit.getCollider().getHeight());
    }

    public void exit() {

    }

    public void registerInputProcessor(InputMultiplexer inputMultiplexer) {

    }

    public void deregisterInputProcessor(InputMultiplexer inputMultiplexer) {

    }
}
