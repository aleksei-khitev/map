package ru.akhitev.rp.fleet.vo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FleetUnitStructure {
    private final Long id;
    private final String name;
    private final int count;
    private Set<FleetUnitStructure> subUnits;
    private Map<ShipShort, Integer> ships;


    public FleetUnitStructure(Long id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public Set<FleetUnitStructure> getSubUnits() {
        return subUnits;
    }

    public void addSubUnits(Set<FleetUnitStructure> subUnits) {
        if (this.subUnits == null) {
            this.subUnits = new HashSet<>();
        }
        this.subUnits.addAll(subUnits);
    }

    public Map<ShipShort, Integer> getShips() {
        return ships;
    }

    public void addShip(ShipShort shipClass, Integer count) {
        if (ships == null) {
            ships = new HashMap<>();
        }
        ships.put(shipClass, count);
    }

    @Override
    public String toString() {
        return count + " x " + name;
    }

    public String toMultiLineString() {
        return name + "\n=======\n" +
                subUnits.stream().map(FleetUnitStructure::toString).collect(Collectors.joining()) + "\n" +
                ships.entrySet().stream().map(e -> e.getValue() + " x " + e.getKey().toString() + "\n").collect(Collectors.joining("\n"));
    }
}
