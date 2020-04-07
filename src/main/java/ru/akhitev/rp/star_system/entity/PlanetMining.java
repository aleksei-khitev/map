package ru.akhitev.rp.star_system.entity;

import ru.akhitev.rp.resource.entity.CriticalResource;

import javax.persistence.*;

@Entity
@Table(name = "planet_resource_mining")
public class PlanetMining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "planet_id")
    private Planet planet;

    @ManyToOne
    @JoinColumn(name = "critical_resource_id")
    private CriticalResource criticalResource;

    @Column(name = "amount_per_year")
    private Long amountPerYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public CriticalResource getCriticalResource() {
        return criticalResource;
    }

    public void setCriticalResource(CriticalResource criticalResource) {
        this.criticalResource = criticalResource;
    }

    public Long getAmountPerYear() {
        return amountPerYear;
    }

    public void setAmountPerYear(Long amountPerYear) {
        this.amountPerYear = amountPerYear;
    }

    @Override
    public String toString() {
        return criticalResource + " (" + amountPerYear + "/год)";
    }
}
