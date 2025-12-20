package com.sundaempire.frontend.unit;

import com.sundaempire.frontend.unit.Unit;

public interface UnitState {
    void enter(Unit unit);
    void update(Unit unit, float delta);
    void exit(Unit unit);
}
