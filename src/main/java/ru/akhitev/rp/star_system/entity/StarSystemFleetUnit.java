package ru.akhitev.rp.star_system.entity;

import ru.akhitev.rp.fleet.entity.FleetUnit;
import ru.akhitev.rp.star_system.entity.StarSystem;

import javax.persistence.*;

@Entity
@Table(name = "star_system_fleet_unit")
public class StarSystemFleetUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "star_system_id", insertable = false, updatable = false)
    private StarSystem starSystem;

    @ManyToOne
    @JoinColumn(name = "fleet_unit_id", insertable = false, updatable = false)
    private FleetUnit fleetUnit;

    @Column(name = "fleet_unit_count")
    private Long fleetUnitCount;

    public Long getId() {
        return id;
    }

    public StarSystem getStarSystem() {
        return starSystem;
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
