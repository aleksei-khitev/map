package ru.akhitev.rp.fleet.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.akhitev.rp.fleet.entity.FleetUnit;
import ru.akhitev.rp.fleet.entity.FleetUnitCompositionByFleetUnits;
import ru.akhitev.rp.fleet.entity.FleetUnitCompositionByShips;
import ru.akhitev.rp.fleet.entity.Ship;

import java.util.*;
import java.util.concurrent.RecursiveTask;

public class ShipsCounterTask extends RecursiveTask<Map<Ship, Integer>> {
    private static Logger logger = LoggerFactory.getLogger(ShipsCounterTask.class);
    private FleetUnit fleetUnit;
    private Map<Ship, Integer> shipsCount;
    private Integer fleetUnitCount;

    public ShipsCounterTask(FleetUnit fleetUnit, Integer fleetUnitCount) {
        this.fleetUnit = fleetUnit;
        this.fleetUnitCount = fleetUnitCount;
    }

    @Override
    protected Map<Ship, Integer> compute() {
        shipsCount = new HashMap<>();
        Set<FleetUnitCompositionByShips> ships = fleetUnit.getCompositionByShips();
        if (ships != null && ships.size() > 0) {
            for (FleetUnitCompositionByShips composition : ships) {
                shipsCount.put(composition.getShip(), composition.getShipCount() * fleetUnitCount);
            }
        }
        Set<FleetUnitCompositionByFleetUnits> units = fleetUnit.getCompositionByFleetUnits();
        if (units != null && units.size() > 0) {
            List<ShipsCounterTask> tasks = new ArrayList<>();
            units.forEach(unitComposition -> tasks.add(new ShipsCounterTask(unitComposition.getChildFleetUnit(), unitComposition.getFleetUnitCount())));
            tasks.forEach(ShipsCounterTask::fork);
            for (ShipsCounterTask task : tasks) {
                Map<Ship, Integer> shipsFromSubUnit = task.join();
                for (Map.Entry<Ship, Integer> entry : shipsFromSubUnit.entrySet()) {
                    if (shipsCount.containsKey(entry.getKey())) {
                        shipsCount.put(entry.getKey(), shipsCount.get(entry.getKey()) + entry.getValue());
                    } else {
                        shipsCount.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        return shipsCount;
    }
}
