package ru.akhitev.rp.map.entity;

import ru.akhitev.rp.fleet.entity.SmallAircraft;

import javax.persistence.*;

@Entity
@Table(name = "star_system_garrison_small_aircraft")
public class StarSystemGarrisonSmallAirCraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "star_system_id", insertable = false, updatable = false)
    private StarSystem starSystem;

    @ManyToOne
    @JoinColumn(name = "small_aircraft_id", insertable = false, updatable = false)
    private SmallAircraft smallAircraft;

    @Column(name = "small_aircraft_count")
    private Long smallAircraftCount;

    public Long getId() {
        return id;
    }

    public StarSystem getStarSystem() {
        return starSystem;
    }

    public SmallAircraft getSmallAircraft() {
        return smallAircraft;
    }

    public Long getSmallAircraftCount() {
        return smallAircraftCount;
    }

    @Override
    public String toString() {
        return smallAircraft + " x " + smallAircraftCount;
    }
}
