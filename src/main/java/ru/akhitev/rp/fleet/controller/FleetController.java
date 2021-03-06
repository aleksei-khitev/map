package ru.akhitev.rp.fleet.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akhitev.rp.conf.AbstractController;
import ru.akhitev.rp.fleet.entity.*;
import ru.akhitev.rp.fleet.service.FleetService;
import ru.akhitev.rp.fleet.vo.FleetUnitStructure;
import ru.akhitev.rp.fleet.vo.FleetUnitSummary;
import ru.akhitev.rp.land_force.entity.LandForce;
import ru.akhitev.rp.ship.entity.Ship;
import ru.akhitev.rp.small_aircraft.entity.SmallAircraft;

import java.util.*;

@Component
public class FleetController extends AbstractController {
    private static final int COLUMN_WIDTH = 400;
    public ComboBox<String> fleetUnits;
    public TextField cost;
    public TextField minimalAndAirCraft;
    public TextField normalAndAirCraft;
    public TextField normalAndAirCraftAndLanding;
    public TextField minimalOnly;
    public TextField airCraft;
    public TextField landingSoldiersAndCrew;
    public TextField smallShips;
    public TextField mediumShips;
    public TextField bigShips;
    public WebView details;
    public TreeView<FleetUnitStructure> structure;
    public TableView<Map.Entry<Ship, Integer>> shipsCount;
    public TableView<Map.Entry<SmallAircraft, Integer>> smallAirCraftCount;
    public TableView<Map.Entry<LandForce, Integer>> landingForcesCount;
    private Set<FleetUnitShort> units;

    @Autowired
    private FleetService fleetService;

    @FXML
    public void initialize() {
        units = fleetService.findAllFleetsInShort();
        units.stream()
                .sorted(Comparator.comparing(FleetUnitShort::level).reversed().thenComparing(FleetUnitShort::getName))
                .map(FleetUnitShort::getName)
                .forEach(fleetUnits.getItems()::add);
        fleetUnits.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            String selectedItem = fleetUnits.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                minimalOnly.clear();
                normalAndAirCraftAndLanding.clear();
                normalAndAirCraft.clear();
                minimalAndAirCraft.clear();
                cost.clear();
                airCraft.clear();
                landingSoldiersAndCrew.clear();
                smallShips.clear();
                bigShips.clear();
                mediumShips.clear();

                Optional<FleetUnitShort> selectedFleetUnit = units.stream().filter(u -> u.getName().equals(selectedItem)).findAny();
                selectedFleetUnit.ifPresent(u -> {
                    FleetUnitSummary summary = fleetService.prepareReportForFleetUnitById(u.getId());
                    cost.setText(String.valueOf(summary.getCost()));
                    minimalAndAirCraft.setText(String.valueOf(summary.getMinimalShipsCrew() + summary.getSmallAircraftCrew()));
                    normalAndAirCraft.setText(String.valueOf(summary.getNormalShipsCrew() + summary.getSmallAircraftCrew()));
                    normalAndAirCraftAndLanding.setText(String.valueOf(summary.getNormalShipsCrew() + summary.getSmallAircraftCrew() + summary.getLandingSoldiersAndCrew()));
                    minimalOnly.setText(String.valueOf(summary.getMinimalShipsCrew()));
                    airCraft.setText(String.valueOf(summary.getSmallAircraftCrew()));
                    landingSoldiersAndCrew.setText(String.valueOf(summary.getLandingSoldiersAndCrew()));
                    summary.getFilteredShipsCount(e -> e.getKey().getSize().getLength() < 500).ifPresent(count -> smallShips.setText(String.valueOf(count)));
                    summary.getFilteredShipsCount(e -> e.getKey().getSize().getLength() >= 500 || e.getKey().getSize().getLength() < 1000).ifPresent(count -> mediumShips.setText(String.valueOf(count)));
                    summary.getFilteredShipsCount(e -> e.getKey().getSize().getLength() >= 1000).ifPresent(count -> bigShips.setText(String.valueOf(count)));
                    structure.setRoot(summary.getStructure());
                    ObservableList<Map.Entry<Ship, Integer>> shipsCountItems = FXCollections.observableArrayList(summary.getShipCounts().entrySet());
                    Collections.sort(shipsCountItems, Comparator.comparing((Map.Entry<Ship, Integer> e) -> e.getKey().getSize().getLength()).reversed());
                    shipsCount.setItems(shipsCountItems);
                    ObservableList<Map.Entry<SmallAircraft, Integer>> smallAircraftsWithCountsItems = FXCollections.observableArrayList(summary.getSmallAircraftsWithCounts().entrySet());
                    smallAirCraftCount.setItems(smallAircraftsWithCountsItems);
                    ObservableList<Map.Entry<LandForce, Integer>> landingForceWithCountsItems = FXCollections.observableArrayList(summary.getLandForcesWithCounts().entrySet());
                    landingForcesCount.setItems(landingForceWithCountsItems);
                });
            }
        });
        structure.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            FleetUnitStructure fleetUnitStructure = newValue.getValue();
            Optional<FleetUnit> unit = fleetService.getFleetUnitById(fleetUnitStructure.getId());
            unit.ifPresent(u -> details.getEngine().loadContent(u.multiLineString()));
        });
        TableColumn<Map.Entry<Ship, Integer>, String> shipColumn = new TableColumn<>("Класс корабля");
        shipColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey().getShipClass()));
        shipColumn.setMinWidth(COLUMN_WIDTH);
        TableColumn<Map.Entry<Ship, Integer>, Integer> shipCount = new TableColumn<>("Количество");
        shipCount.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());
        shipsCount.getColumns().addAll(shipColumn, shipCount);
        shipsCount.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            Ship ship = newValue.getKey();
            details.getEngine().loadContent(ship.toHtmlString());
        });
        TableColumn<Map.Entry<SmallAircraft, Integer>, String> smallAirCraftColumn = new TableColumn<>("Малый аппарат");
        smallAirCraftColumn.setMinWidth(COLUMN_WIDTH);
        smallAirCraftColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey().getName()));
        TableColumn<Map.Entry<SmallAircraft, Integer>, Integer> airCraftCount = new TableColumn<>("Количество");
        airCraftCount.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());
        smallAirCraftCount.getColumns().addAll(smallAirCraftColumn, airCraftCount);
        smallAirCraftCount.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            SmallAircraft aircraft = newValue.getKey();
            details.getEngine().loadContent(aircraft.toHtmlString());
        });
        TableColumn<Map.Entry<LandForce, Integer>, String> landingForceColumn = new TableColumn<>("Единицы");
        landingForceColumn.setMinWidth(COLUMN_WIDTH);
        landingForceColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey().getName()));
        TableColumn<Map.Entry<LandForce, Integer>, Integer> landingForceCount = new TableColumn<>("Количество");
        landingForceCount.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());
        landingForcesCount.getColumns().addAll(landingForceColumn, landingForceCount);
        landingForcesCount.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            LandForce landForce = newValue.getKey();
            details.getEngine().loadContent(landForce.toHtmlString());
        });
    }
}
