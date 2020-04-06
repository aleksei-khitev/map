package ru.akhitev.rp.star_system.entity;

import ru.akhitev.rp.land_force.entity.LandForce;

import javax.persistence.*;

@Entity
@Table(name = "planet_garrison")
public class PlanetGarrison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "planet_id", insertable = false, updatable = false)
    private Planet planet;

    @ManyToOne
    @JoinColumn(name = "land_force_id", insertable = false, updatable = false)
    private LandForce landForce;

    @Column(name = "land_force_count")
    private Long landForceCount;

    public Long getId() {
        return id;
    }

    public Planet getPlanet() {
        return planet;
    }

    public LandForce getLandForce() {
        return landForce;
    }

    public Long getLandForceCount() {
        return landForceCount;
    }

    @Override
    public String toString() {
        return landForce + " x " + landForceCount;
    }
}
