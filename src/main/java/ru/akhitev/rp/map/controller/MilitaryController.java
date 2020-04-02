package ru.akhitev.rp.map.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akhitev.rp.conf.AbstractController;
import ru.akhitev.rp.fleet.entity.FleetUnit;
import ru.akhitev.rp.fleet.entity.LandForce;
import ru.akhitev.rp.fleet.entity.Ship;
import ru.akhitev.rp.fleet.entity.SmallAircraft;
import ru.akhitev.rp.fleet.service.FleetService;
import ru.akhitev.rp.fleet.vo.FleetUnitSummary;
import ru.akhitev.rp.map.entity.*;
import ru.akhitev.rp.map.repository.StarSystemRepository;
import ru.akhitev.rp.map.repository.StateHoodRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class MilitaryController extends AbstractController {
    private static Logger logger = LoggerFactory.getLogger(MilitaryController.class);
    @FXML private ComboBox<StateHood> state;
    @FXML private WebView landForces;
    private StateHoodRepository stateHoodRepository;
    private StarSystemRepository starSystemRepository;
    private FleetService fleetService;

    @Autowired
    public MilitaryController(StateHoodRepository stateHoodRepository, StarSystemRepository starSystemRepository, FleetService fleetService) {
        this.stateHoodRepository = stateHoodRepository;
        this.starSystemRepository = starSystemRepository;
        this.fleetService = fleetService;
    }

    @FXML
    public void initialize() {
        logger.info("stateHoods: " + starSystemRepository.findAll());
        stateHoodRepository.findAll().forEach(state.getItems()::add);
        state.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            Map<LandForce, Long> landForceCount = new HashMap<>();
            starSystemRepository.findByStatehood(newValue).stream()
                    .filter(star -> star.getGarrison() != null && star.getGarrison().size() > 0)
                    .map(StarSystem::getGarrison).forEach(garrisons -> {
                        for (StarSystemGarrison garrison : garrisons) {
                            LandForce landForce = garrison.getLandForce();
                            if (landForceCount.containsKey(landForce)) {
                                landForceCount.put(landForce, landForceCount.get(landForce) + garrison.getLandForceCount());
                            } else {
                                landForceCount.put(landForce, garrison.getLandForceCount());
                            }
                        }
            });
            Map<SmallAircraft, Long> smallAircraftCount = new HashMap<>();
            starSystemRepository.findByStatehood(newValue).stream()
                    .filter(star -> star.getGarrisonSmallAirCrafts() != null && star.getGarrisonSmallAirCrafts().size() > 0)
                    .map(StarSystem::getGarrisonSmallAirCrafts).forEach(garrisonAirCrafts -> {
                        for (StarSystemGarrisonSmallAirCraft garrisonSmallAirCraft : garrisonAirCrafts) {
                            SmallAircraft smallAircraft = garrisonSmallAirCraft.getSmallAircraft();
                            if (smallAircraftCount.containsKey(smallAircraft)) {
                                smallAircraftCount.put(smallAircraft, smallAircraftCount.get(smallAircraft) + garrisonSmallAirCraft.getSmallAircraftCount());
                            } else {
                                smallAircraftCount.put(smallAircraft, garrisonSmallAirCraft.getSmallAircraftCount());
                            }
                        }
                    });
            StringBuilder builder = new StringBuilder("<h4>Сила наземных гарнизонов</h4>");
            builder.append("<h5>Сухопутные силы планетарной обороны:</h5><ul>");
            AtomicLong summaryCrewAndSoldiers = new AtomicLong(0);
            for (Map.Entry<LandForce, Long> entry : landForceCount.entrySet()) {
                builder.append("<li><lb>").append(entry.getKey().getName()).append(":</b> ")
                        .append(entry.getValue()).append("</li>");
                if (entry.getKey().getId() == 8) {
                    builder.append("<li><lb>").append("Штат баз").append(":</b> ")
                            .append(entry.getValue() * entry.getKey().getCrew()).append("</li>");
                    summaryCrewAndSoldiers.addAndGet(entry.getValue() * entry.getKey().getCrew());
                } else if (entry.getKey().getId() == 6 || entry.getKey().getId() == 7) {
                    builder.append("<li><lb>").append("Экипажи техники ").append(entry.getKey().getName()).append(":</b> ")
                            .append(entry.getValue() * entry.getKey().getCrew()).append("</li>");
                    summaryCrewAndSoldiers.addAndGet(entry.getValue() * entry.getKey().getCrew());
                } else {
                    summaryCrewAndSoldiers.addAndGet(entry.getValue());
                }
            }
            builder.append("</ul><h5>Малые летательные аппараты силы планетарной обороны:</h5><ul>");
            for (Map.Entry<SmallAircraft, Long> entry : smallAircraftCount.entrySet()) {
                builder.append("<li><lb>").append(entry.getKey().getName()).append(":</b> ")
                        .append(entry.getValue()).append("</li>");
                summaryCrewAndSoldiers.addAndGet(entry.getValue() * entry.getKey().getCrew());
            }
            Map<Ship, Long> ships = new HashMap<>();
            starSystemRepository.findByStatehood(newValue).stream()
                    .filter(star -> star.getStarSystemFleetUnits() != null && star.getStarSystemFleetUnits().size() > 0)
                    .map(StarSystem::getStarSystemFleetUnits).forEach(fleetUnits -> {
                for (StarSystemFleetUnit fleetUnitCount : fleetUnits) {
                    FleetUnitSummary summary = fleetService.prepareReportForFleetUnitById(fleetUnitCount.getFleetUnit().getId());
                    for (Map.Entry<Ship, Integer> entry : summary.getShipCounts().entrySet()) {
                        if (ships.containsKey(entry.getKey())) {
                            ships.put(entry.getKey(), ships.get(entry.getKey()) + entry.getValue());
                        } else {
                            ships.put(entry.getKey(), Long.valueOf(entry.getValue()));
                        }
                    }
                    for (Map.Entry<SmallAircraft, Integer> entry : summary.getSmallAircraftsWithCounts().entrySet()) {
                        if (smallAircraftCount.containsKey(entry.getKey())) {
                            smallAircraftCount.put(entry.getKey(), smallAircraftCount.get(entry.getKey()) + entry.getValue());
                        } else {
                            smallAircraftCount.put(entry.getKey(), Long.valueOf(entry.getValue()));
                        }
                    }
                    for (Map.Entry<LandForce, Integer> entry : summary.getLandForcesWithCounts().entrySet()) {
                        if (landForceCount.containsKey(entry.getKey())) {
                            landForceCount.put(entry.getKey(), landForceCount.get(entry.getKey()) + entry.getValue());
                        } else {
                            landForceCount.put(entry.getKey(), Long.valueOf(entry.getValue()));
                        }
                    }
                    summaryCrewAndSoldiers.addAndGet(summary.getNormalShipsCrew() + summary.getLandingSoldiersAndCrew() + summary.getSmallAircraftCrew());
                }
            });
            builder.append("</ul><h5>Космические силы:</h5><ul>");
            for (Map.Entry<Ship, Long> entry : ships.entrySet()) {
                builder.append("<li><lb>").append(entry.getKey().getShipClass()).append(":</b> ")
                        .append(entry.getValue()).append("</li>");
            }
            builder.append("</ul><h4>Суммарные сухопутные силы гарнизонов и кораблей:</h4><ul>");
            for (Map.Entry<LandForce, Long> entry : landForceCount.entrySet()) {
                builder.append("<li><lb>").append(entry.getKey().getName()).append(":</b> ")
                        .append(entry.getValue()).append("</li>");
                if (entry.getKey().getId() == 8) {
                    builder.append("<li><lb>").append("Штат баз").append(":</b> ")
                            .append(entry.getValue() * entry.getKey().getCrew()).append("</li>");
                    summaryCrewAndSoldiers.addAndGet(entry.getValue() * entry.getKey().getCrew());
                } else if (entry.getKey().getId() == 6 || entry.getKey().getId() == 7) {
                    builder.append("<li><lb>").append("Экипажи техники ").append(entry.getKey().getName()).append(":</b> ")
                            .append(entry.getValue() * entry.getKey().getCrew()).append("</li>");
                    summaryCrewAndSoldiers.addAndGet(entry.getValue() * entry.getKey().getCrew());
                } else {
                    summaryCrewAndSoldiers.addAndGet(entry.getValue());
                }
            }
            builder.append("</ul><h4>Суммарные малые летательные аппараты гарнизонов и кораблей:</h4><ul>");
            for (Map.Entry<SmallAircraft, Long> entry : smallAircraftCount.entrySet()) {
                builder.append("<li><lb>").append(entry.getKey().getName()).append(":</b> ")
                        .append(entry.getValue()).append("</li>");
                summaryCrewAndSoldiers.addAndGet(entry.getValue() * entry.getKey().getCrew());
            }
            builder.append("</ul>").append("<hr/>")
                .append("<b>Суммарное количество солдат, экипажей, персонала: </b>")
                .append(summaryCrewAndSoldiers.get());
            landForces.getEngine().loadContent(builder.toString());
        });
    }
}
