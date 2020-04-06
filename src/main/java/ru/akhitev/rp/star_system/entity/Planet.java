package ru.akhitev.rp.star_system.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "star_system_id")
    private StarSystem starSystem;

    @Column
    private String name;

    @Column
    private Long population;

    @OneToMany(mappedBy = "planet",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<PlanetResource> planetResources;

    @OneToMany(mappedBy = "planet",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<PlanetMining> planetMinings;

    @OneToMany(mappedBy = "planet",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<PlanetProduction> planetProductions;

    @OneToMany(mappedBy = "planet",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<PlanetGarrison> garrison;

    @OneToMany(mappedBy = "planet",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<PlanetGarrisonSmallAirCraft> garrisonSmallAirCrafts;

    @OneToMany(mappedBy = "planet",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<PlanetDefenceFleetUnit> planetDefenceFleetUnits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StarSystem getStarSystem() {
        return starSystem;
    }

    public void setStarSystem(StarSystem starSystem) {
        this.starSystem = starSystem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Set<PlanetResource> getPlanetResources() {
        return planetResources;
    }

    public void setPlanetResources(Set<PlanetResource> planetResources) {
        this.planetResources = planetResources;
    }

    public Set<PlanetMining> getPlanetMinings() {
        return planetMinings;
    }

    public void setPlanetMinings(Set<PlanetMining> planetMinings) {
        this.planetMinings = planetMinings;
    }

    public Set<PlanetProduction> getPlanetProductions() {
        return planetProductions;
    }

    public void setPlanetProductions(Set<PlanetProduction> planetProductions) {
        this.planetProductions = planetProductions;
    }

    public Set<PlanetGarrison> getGarrison() {
        return garrison;
    }

    public void setGarrison(Set<PlanetGarrison> garrison) {
        this.garrison = garrison;
    }

    public Set<PlanetGarrisonSmallAirCraft> getGarrisonSmallAirCrafts() {
        return garrisonSmallAirCrafts;
    }

    public void setGarrisonSmallAirCrafts(Set<PlanetGarrisonSmallAirCraft> garrisonSmallAirCrafts) {
        this.garrisonSmallAirCrafts = garrisonSmallAirCrafts;
    }

    public Set<PlanetDefenceFleetUnit> getPlanetDefenceFleetUnits() {
        return planetDefenceFleetUnits;
    }

    public void setPlanetDefenceFleetUnits(Set<PlanetDefenceFleetUnit> planetDefenceFleetUnits) {
        this.planetDefenceFleetUnits = planetDefenceFleetUnits;
    }

    @Override
    public String toString() {
        return name;
    }

    public String toHtmlString() {
        final String format = "<h3>Система</h3>";
        StringBuilder builder = new StringBuilder("<b>")
                .append(name).append("</b>");
        builder.append((garrison !=null && garrison.size() > 0)?"<h5>Сухопутный гарнизон:</h5><ul>" + garrison.stream()
                .map(PlanetGarrison::toString)
                .map(s -> "<li>" + s + "</li>")
                .collect(Collectors.joining()) + "</ul>":"" );
        builder.append((garrisonSmallAirCrafts !=null && garrisonSmallAirCrafts.size() > 0)?"<h5>МЛА гарнизона:</h5><ul>" + garrisonSmallAirCrafts.stream()
                .map(PlanetGarrisonSmallAirCraft::toString)
                .map(s -> "<li>" + s + "</li>")
                .collect(Collectors.joining()) + "</ul>":"" );
        builder.append((planetDefenceFleetUnits !=null && planetDefenceFleetUnits.size() > 0)?"<h5>Космические силы гарнизона:</h5>" + planetDefenceFleetUnits.stream()
                .map(PlanetDefenceFleetUnit::toString)
                .map(s -> s + "<br/>")
                .collect(Collectors.joining()):"" );
        return builder.toString();
    }
}
