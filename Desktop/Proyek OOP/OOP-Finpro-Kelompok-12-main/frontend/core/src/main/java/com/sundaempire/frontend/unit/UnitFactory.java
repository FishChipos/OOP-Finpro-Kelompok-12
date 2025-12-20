package com.sundaempire.frontend.unit;

public class UnitFactory {

    public enum UnitType {
        INFANTRY,
        ARCHER
    }

    public Unit createUnit(UnitType type) {

        switch (type) {
            case INFANTRY:
                return new Unit("Infantry", 100, 10);

            case ARCHER:
                return new Unit("Archer", 80, 15);

            default:
                throw new IllegalArgumentException("Unknown unit type");
        }
    }

}
