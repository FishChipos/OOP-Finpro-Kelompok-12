package com.sundaempire.frontend.unit;

public class UnitFactory {

    public enum UnitType {
        SWORDSMAN,
        ARCHER,
        EXPLORER
    }

    public Unit createUnit(UnitType type, Unit.Owner owner) {

        switch (type) {
            case SWORDSMAN:
                return new Unit("Swordsman", 100, 10, 1, owner);

            case ARCHER:
                return new Unit("Archer", 80, 15, 1, owner);

            case EXPLORER:
                return new Unit("Explorer", 70, 5, 2, owner);

            default:
                throw new IllegalArgumentException("Unknown unit type");
        }
    }
}
