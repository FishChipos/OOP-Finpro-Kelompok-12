package com.sundaempire.frontend.unit;

import java.util.ArrayList;
import java.util.List;

public class UnitFactory {

    public enum UnitType {
        SWORDSMAN,
        ARCHER,
        EXPLORER
    }

    public Unit createUnit(UnitType type) {

        switch (type) {
            case SWORDSMAN:
                return new Unit("Swordsman", 100, 10, 1);

            case ARCHER:
                return new Unit("Archer", 80, 15, 1);

            case EXPLORER:
                return new Unit("Explorer", 70, 5, 2);

            default:
                throw new IllegalArgumentException("Unknown unit type");
        }
    }

    public List<Unit> createUnits(UnitType type, int count) {
        List<Unit> units = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            units.add(createUnit(type));
        }
        return units;
    }

    public static void main(String[] args) {
        UnitFactory factory = new UnitFactory();

        Unit swordsman = factory.createUnit(UnitType.SWORDSMAN);
        System.out.println("Created unit: " + swordsman.getName());

        Unit explorer = factory.createUnit(UnitType.EXPLORER);
        System.out.println("Created unit: " + explorer.getName());

        List<Unit> explorerGroup = factory.createUnits(UnitType.EXPLORER, 3);
        System.out.println("Created explorer group of size: " + explorerGroup.size());
    }

}
