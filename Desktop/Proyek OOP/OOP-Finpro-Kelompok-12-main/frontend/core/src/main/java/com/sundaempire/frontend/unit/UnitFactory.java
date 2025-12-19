package com.sundaempire.frontend.unit;

import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.unit.Unit;

public interface UnitFactory {
    Unit create(Vector2 position);
}
