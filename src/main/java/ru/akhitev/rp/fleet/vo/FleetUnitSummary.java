package ru.akhitev.rp.fleet.vo;

import javafx.scene.control.TreeItem;
import ru.akhitev.rp.fleet.entity.LandForce;
import ru.akhitev.rp.fleet.entity.Ship;
import ru.akhitev.rp.fleet.entity.SmallAircraft;

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class FleetUnitSummary {
    private final TreeItem<FleetUnitStructure> structure;
    private final Map<Ship, Integer> shipCounts;
    private final Map<SmallAircraft, Integer> smallAircraftsWithCounts;
    private final Map<LandForce, Integer> landForcesWithCounts;
    private final Long cost;
    private final Long minimalShipsCrew;
    private final Long normalShipsCrew;
    private final Long smallAircraftCrew;
    private final Long landingSoldiersAndCrew;

    public FleetUnitSummary(TreeItem<FleetUnitStructure> structure, Map<Ship, Integer> shipCounts, Map<SmallAircraft, Integer> smallAircraftsWithCounts, Map<LandForce, Integer> landForcesWithCounts, Long cost, Long minimalShipsCrew, Long normalShipsCrew, Long smallAircraftCrew, Long landingSoldiersAndCrew) {
        this.structure = structure;
        this.shipCounts = shipCounts;
        this.smallAircraftsWithCounts = smallAircraftsWithCounts;
        this.landForcesWithCounts = landForcesWithCounts;
        this.cost = cost;
        this.minimalShipsCrew = minimalShipsCrew;
        this.normalShipsCrew = normalShipsCrew;
        this.smallAircraftCrew = smallAircraftCrew;
        this.landingSoldiersAndCrew = landingSoldiersAndCrew;
    }

    public TreeItem<FleetUnitStructure> getStructure() {
        return structure;
    }

    public Map<Ship, Integer> getShipCounts() {
        return shipCounts;
    }

    public Map<SmallAircraft, Integer> getSmallAircraftsWithCounts() {
        return smallAircraftsWithCounts;
    }

    public Map<LandForce, Integer> getLandForcesWithCounts() {
        return landForcesWithCounts;
    }

    public Long getCost() {
        return cost;
    }

    public Long getMinimalShipsCrew() {
        return minimalShipsCrew;
    }

    public Long getNormalShipsCrew() {
        return normalShipsCrew;
    }

    public Long getSmallAircraftCrew() {
        return smallAircraftCrew;
    }

    public Long getLandingSoldiersAndCrew() {
        return landingSoldiersAndCrew;
    }

    public Optional<Integer> getFilteredShipsCount(Predicate<Map.Entry<Ship, Integer>> cause) {
        return shipCounts.entrySet().stream()
                .filter(cause)
                .map(Map.Entry::getValue).reduce(Integer::sum);
    }

    public static class Builder {
        private TreeItem<FleetUnitStructure> structure;
        private Map<Ship, Integer> shipCounts;
        private Map<SmallAircraft, Integer> smallAircraftsWithCounts;
        private Map<LandForce, Integer> landForcesWithCounts;
        private Long cost;
        private Long minimalShipsCrew;
        private Long normalShipsCrew;
        private Long smallAircraftCrew;
        private Long landingSoldiersAndCrew;

        public Builder structure(TreeItem<FleetUnitStructure> structure) {
            this.structure = structure;
            return this;
        }

        public Builder shipCounts(Map<Ship, Integer> shipCounts) {
            this.shipCounts = shipCounts;
            return this;
        }

        public Builder smallAircraftsWithCounts(Map<SmallAircraft, Integer> smallAircraftsWithCounts) {
            this.smallAircraftsWithCounts = smallAircraftsWithCounts;
            return this;
        }

        public Builder landForcesWithCounts(Map<LandForce, Integer> landForcesWithCounts) {
            this.landForcesWithCounts = landForcesWithCounts;
            return this;
        }

        public Builder cost(Long cost) {
            this.cost = cost;
            return this;
        }

        public Builder minimalShipsCrew(Long minimalShipsCrew) {
            this.minimalShipsCrew = minimalShipsCrew;
            return this;
        }

        public Builder normalShipsCrew(Long normalShipsCrew) {
            this.normalShipsCrew = normalShipsCrew;
            return this;
        }

        public Builder smallAircraftCrew(Long smallAircraftCrew) {
            this.smallAircraftCrew = smallAircraftCrew;
            return this;
        }

        public Builder landingSoldiersAndCrew(Long landingSoldiersAndCrew) {
            this.landingSoldiersAndCrew = landingSoldiersAndCrew;
            return this;
        }

        public FleetUnitSummary build() {
            return new FleetUnitSummary(this.structure, this.shipCounts, this.smallAircraftsWithCounts, this.landForcesWithCounts, this.cost, this.minimalShipsCrew, this.normalShipsCrew, this.smallAircraftCrew, this.landingSoldiersAndCrew);
        }
    }
}
