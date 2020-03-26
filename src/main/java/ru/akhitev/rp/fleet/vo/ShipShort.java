package ru.akhitev.rp.fleet.vo;

public class ShipShort {
    private final Long id;
    private final String type;
    private final String shipClass;

    public ShipShort(Long id, String type, String shipClass) {
        this.id = id;
        this.type = type;
        this.shipClass = shipClass;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getShipClass() {
        return shipClass;
    }

    @Override
    public String toString() {
        return type + " класса " + shipClass;
    }
}
