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

    @Column
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

    @Version
    @Column(name = "version")
    private int version;

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
        return name + "\n========\n" +
                "Минимальное звание для командования: " + minimumCommandRank.getName() + "\n" +
                "\nСостав:\n-------\n " +
                ((compositionByFleetUnits !=null && compositionByFleetUnits.size() > 0)?"Единицы флота:\n" + compositionByFleetUnits.stream().map(FleetUnitCompositionByFleetUnits::toString).collect(Collectors.joining("\n")) + "\n":"" ) +
                ((compositionByShips !=null && compositionByShips.size() > 0)?"Корабли:\n" + compositionByShips.stream().map(FleetUnitCompositionByShips::toString).collect(Collectors.joining("\n")) + "\n":"" ) +
                ((comments != null)?"\nКомментарии:\n" + comments:"");
    }
}
