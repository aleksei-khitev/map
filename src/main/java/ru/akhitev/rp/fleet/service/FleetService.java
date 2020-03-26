package ru.akhitev.rp.fleet.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhitev.rp.fleet.entity.FleetUnit;
import ru.akhitev.rp.fleet.repo.FleetUnitRepo;
import ru.akhitev.rp.fleet.entity.FleetUnitShort;
import ru.akhitev.rp.fleet.util.FleetUnitStructureBuildingTask;
import ru.akhitev.rp.fleet.util.ShipsCounterTask;
import ru.akhitev.rp.fleet.vo.FleetUnitStructure;
import ru.akhitev.rp.fleet.vo.FleetUnitSummary;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

@Service
public class FleetService {
    private static Logger logger = LoggerFactory.getLogger(FleetService.class);
    private FleetUnitRepo fleetUnitRepo;
    private ReportService reportService;

    @Autowired
    public FleetService(FleetUnitRepo fleetUnitRepo, ReportService reportService) {
        this.fleetUnitRepo = fleetUnitRepo;
        this.reportService = reportService;
    }

    public Set<FleetUnitShort> findAllFleetsInShort() {
        return fleetUnitRepo.findAllForShortForm();
    }

    public FleetUnitSummary prepareReportForFleetUnitById(Long id) {
        FleetUnit unit = fleetUnitRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Такая единица флота не найдена", null));
        return reportService.prepareSummary(unit);
    }

    public Optional<FleetUnit> getFleetUnitById(Long id) {
        return fleetUnitRepo.findById(id);
    }
}
