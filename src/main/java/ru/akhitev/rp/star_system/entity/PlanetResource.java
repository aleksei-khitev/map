package ru.akhitev.rp.star_system.entity;

import ru.akhitev.rp.resource.entity.CriticalResource;

import javax.persistence.*;

@Entity
@Table(name = "planet_resource")
public class PlanetResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "planet_id", insertable = false, updatable = false)
    private Planet planet;

    @ManyToOne
    @JoinColumn(name = "critical_resource_id", insertable = false, updatable = false)
    private CriticalResource criticalResource;

    @Column(name = "amount")
    private Long amount;

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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return criticalResource + " x " + amount;
    }
}
