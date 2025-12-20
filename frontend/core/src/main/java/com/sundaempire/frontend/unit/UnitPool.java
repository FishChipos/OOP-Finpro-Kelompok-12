package com.sundaempire.frontend.unit;

import java.util.Stack;

public class UnitPool {

    private final Stack<Unit> pool = new Stack<>();

    public Unit obtain(UnitFactory factory, UnitFactory.UnitType type, Unit.Owner owner) {
        if (!pool.isEmpty()) {
            return pool.pop();
        }
        return factory.createUnit(type, owner);
    }

    public void free(Unit unit) {
        pool.push(unit);
    }
}
