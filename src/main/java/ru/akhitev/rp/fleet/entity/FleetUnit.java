package ru.akhitev.rp.fleet.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "fleet_unit")
public class FleetUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "minimum_command_rank_id")
    private CommandRank minimumCommandRank;

    @Column(columnDefinition="LONGTEXT")
    private String comments;

    @OneToMany(mappedBy = "fleetUnit",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<FleetUnitCompositionByShips> compositionByShips;

    @OneToMany(mappedBy = "parentFleetUnit",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<FleetUnitCompositionByFleetUnits> compositionByFleetUnits;

    @OneToMany(mappedBy = "childFleetUnit",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<FleetUnitCompositionByFleetUnits> childForFleetUnits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommandRank getMinimumCommandRank() {
        return minimumCommandRank;
    }

    public void setMinimumCommandRank(CommandRank minimumCommandRank) {
        this.minimumCommandRank = minimumCommandRank;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<FleetUnitCompositionByShips> getCompositionByShips() {
        return compositionByShips;
    }

    public void setCompositionByShips(Set<FleetUnitCompositionByShips> compositionByShips) {
        this.compositionByShips = compositionByShips;
    }

    public Set<FleetUnitCompositionByFleetUnits> getCompositionByFleetUnits() {
        return compositionByFleetUnits;
    }

    public void setCompositionByFleetUnits(Set<FleetUnitCompositionByFleetUnits> compositionByFleetUnits) {
        this.compositionByFleetUnits = compositionByFleetUnits;
    }

    @Override
    public String toString() {
        return name;
    }

    public String multiLineString() {
        return "<h3>" + name + "</h3>\n<hr/>" +
                "<b>Минимальное звание для командования</b>: " + minimumCommandRank.getName() + "<br/>" +
                "<h4>Состав:</h4>" +
                ((compositionByFleetUnits !=null && compositionByFleetUnits.size() > 0)?"<h5>Единицы флота:</h5>" + compositionByFleetUnits.stream()
                        .map(FleetUnitCompositionByFleetUnits::toString)
                        .map(s -> "<li>" + s + "</li>")
                        .collect(Collectors.joining()) + "</ul>":"" ) +
                ((compositionByShips !=null && compositionByShips.size() > 0)?"<h5>Корабли:</h5><ul>" + compositionByShips.stream()
                        .map(FleetUnitCompositionByShips::toString)
                        .map(s -> "<li>" + s + "</li>")
                        .collect(Collectors.joining()) + "</ul>":"" ) +
                ((comments != null)?"<h4>Комментарии:</h4>" + comments:"");
    }
}
