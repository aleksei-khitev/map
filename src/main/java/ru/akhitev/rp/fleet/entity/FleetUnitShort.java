package ru.akhitev.rp.fleet.entity;

import org.springframework.util.comparator.Comparators;

import java.util.Comparator;

public interface FleetUnitShort {
    Long getId();
    String getName();

    default int level() {
        if (getName().toLowerCase().contains("линия")) return 0;
        else if (getName().toLowerCase().contains("эскадра")) return 1;
        else if (getName().toLowerCase().contains("группировка")) return 2;
        else if (getName().toLowerCase().contains("соединение")) return 2;
        else if (getName().toLowerCase().contains("флот")) return 3;
        else return 4;
    }
}
