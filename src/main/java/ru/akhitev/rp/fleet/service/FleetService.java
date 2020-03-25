package ru.akhitev.rp.fleet.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhitev.rp.fleet.repo.FleetUnitRepo;

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

    public void listAllFleets() {
        fleetUnitRepo.findAll().forEach(unit -> logger.info(unit.toString()));
    }

    public String prepareReportForFleetUnitById(Long id) {
        return reportService.prepare(fleetUnitRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Такая единица флота не найдена", null)));
    }
}
