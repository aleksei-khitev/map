package ru.akhitev.rp.star_system.entity;

import ru.akhitev.rp.fleet.entity.FleetUnit;

import javax.persistence.*;

@Entity
@Table(name = "planet_defence_fleet_unit")
public class PlanetDefenceFleetUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "planet_id", insertable = false, updatable = false)
    private Planet planet;

    @ManyToOne
    @JoinColumn(name = "fleet_unit_id", insertable = false, updatable = false)
    private FleetUnit fleetUnit;

    @Column(name = "fleet_unit_count")
    private Long fleetUnitCount;

    public Long getId() {
        return id;
    }

    public Planet getPlanet() {
        return planet;
    }

    public FleetUnit getFleetUnit() {
        return fleetUnit;
    }

    public Long getFleetUnitCount() {
        return fleetUnitCount;
    }

    @Override
    public String toString() {
        return fleetUnit + " x " + fleetUnitCount;
    }
}
