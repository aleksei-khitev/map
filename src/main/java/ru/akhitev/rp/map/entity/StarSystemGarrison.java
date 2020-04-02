package ru.akhitev.rp.map.entity;

import ru.akhitev.rp.fleet.entity.LandForce;

import javax.persistence.*;

@Entity
@Table(name = "star_system_garrison")
public class StarSystemGarrison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "star_system_id", insertable = false, updatable = false)
    private StarSystem starSystem;

    @ManyToOne
    @JoinColumn(name = "land_force_id", insertable = false, updatable = false)
    private LandForce landForce;

    @Column(name = "land_force_count")
    private Long landForceCount;

    public Long getId() {
        return id;
    }

    public StarSystem getStarSystem() {
        return starSystem;
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
