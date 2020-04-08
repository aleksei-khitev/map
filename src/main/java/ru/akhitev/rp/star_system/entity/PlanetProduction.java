package ru.akhitev.rp.star_system.entity;

import ru.akhitev.rp.production.entity.CriticalProduction;

import javax.persistence.*;

@Entity
@Table(name = "planet_production")
public class PlanetProduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "planet_id")
    private Planet planet;

    @ManyToOne
    @JoinColumn(name = "critical_production_id")
    private CriticalProduction criticalProduction;

    @Column
    private Integer level;

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

    public CriticalProduction getCriticalProduction() {
        return criticalProduction;
    }

    public void setCriticalProduction(CriticalProduction criticalProduction) {
        this.criticalProduction = criticalProduction;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return criticalProduction + " (уровень " + level + ")";
    }
}
