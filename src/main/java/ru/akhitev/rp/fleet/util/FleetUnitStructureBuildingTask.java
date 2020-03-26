package ru.akhitev.rp.fleet.util;

import javafx.scene.control.TreeItem;
import ru.akhitev.rp.fleet.entity.FleetUnit;
import ru.akhitev.rp.fleet.entity.FleetUnitCompositionByFleetUnits;
import ru.akhitev.rp.fleet.entity.FleetUnitCompositionByShips;
import ru.akhitev.rp.fleet.vo.FleetUnitStructure;
import ru.akhitev.rp.fleet.vo.ShipShort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class FleetUnitStructureBuildingTask extends RecursiveTask<TreeItem<FleetUnitStructure>> {
    private FleetUnit fleetUnit;
    private Integer count;

    public FleetUnitStructureBuildingTask(FleetUnit fleetUnit, Integer count) {
        this.fleetUnit = fleetUnit;
        this.count = count;
    }

    @Override
    protected TreeItem compute() {
        FleetUnitStructure fleetUnitStructure = new FleetUnitStructure(fleetUnit.getId(), fleetUnit.getName(), count);
        TreeItem<FleetUnitStructure> treeItem = new TreeItem<>(fleetUnitStructure);
        Set<FleetUnitCompositionByFleetUnits> units = fleetUnit.getCompositionByFleetUnits();
        if (units != null && units.size() > 0) {
            List<FleetUnitStructureBuildingTask> tasks = new ArrayList<>();
            units.forEach(unitComposition ->
                    tasks.add(new FleetUnitStructureBuildingTask(unitComposition.getChildFleetUnit(), unitComposition.getFleetUnitCount())));
            tasks.forEach(FleetUnitStructureBuildingTask::fork);
            tasks.stream().map(ForkJoinTask::join)
                    .forEach(i -> treeItem.getChildren().add(i));
        }
        Set<FleetUnitCompositionByShips> ships = fleetUnit.getCompositionByShips();
        if (ships != null && ships.size() > 0) {
            ships.forEach(s -> fleetUnitStructure.addShip(
                    new ShipShort(s.getShip().getId(), s.getShip().getType(), s.getShip().getShipClass()),
                    s.getShipCount()));
        }
        return treeItem;
    }
}
