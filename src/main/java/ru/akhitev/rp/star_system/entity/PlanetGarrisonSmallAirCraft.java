package ru.akhitev.rp.star_system.entity;

import ru.akhitev.rp.small_aircraft.entity.SmallAircraft;

import javax.persistence.*;

@Entity
@Table(name = "planet_garrison_small_aircraft")
public class PlanetGarrisonSmallAirCraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "planet_id", insertable = false, updatable = false)
    private Planet planet;

    @ManyToOne
    @JoinColumn(name = "small_aircraft_id", insertable = false, updatable = false)
    private SmallAircraft smallAircraft;

    @Column(name = "small_aircraft_count")
    private Long smallAircraftCount;

    public Long getId() {
        return id;
    }

    public Planet getPlanet() {
        return planet;
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
