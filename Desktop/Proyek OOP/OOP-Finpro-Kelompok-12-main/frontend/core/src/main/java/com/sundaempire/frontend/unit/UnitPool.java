package com.sundaempire.frontend.unit;

public interface UnitPool {
    Unit obtain();
    void free(Unit unit);
}
