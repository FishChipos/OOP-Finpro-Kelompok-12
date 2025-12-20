package com.sundaempire.frontend.unit;

import java.util.Stack;

public class UnitPool {

    private final Stack<Unit> pool = new Stack<>();

    public Unit obtain(UnitFactory factory, UnitFactory.UnitType type) {
        if (!pool.isEmpty()) {
            return pool.pop();
        }
        return factory.createUnit(type);
    }

    public void free(Unit unit) {
        pool.push(unit);
    }
}
