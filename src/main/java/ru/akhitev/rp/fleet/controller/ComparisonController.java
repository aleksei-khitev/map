package ru.akhitev.rp.fleet.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akhitev.rp.conf.AbstractController;
import ru.akhitev.rp.fleet.entity.*;
import ru.akhitev.rp.fleet.service.FleetService;
import ru.akhitev.rp.fleet.service.ShipService;
import ru.akhitev.rp.fleet.vo.FleetUnitSummary;
import ru.akhitev.rp.fleet.vo.ShipShort;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class ComparisonController extends AbstractController {
    public ComboBox<ShipShort> shipForComparison1;
    public ComboBox<ShipShort> shipForComparison2;
    public Button shipCompare;
    public WebView shipComparisonResult;
    public ComboBox<String> fleetUnitForComparison1;
    public ComboBox<String> fleetUnitForComparison2;
    public Button fleetUnitCompare;
    public WebView fleetUnitComparisonResult;
    public ComboBox<ShipShort> shipAndFleetUnitForComparison1;
    public ComboBox<String> shipAndFleetUnitForComparison2;
    public Button shipAndFleetUnitCompare;
    public WebView shipAndFleetUnitComparisonResult;
    private Predicate<ShipHangar> fightersAndGunBoats = sh -> sh.getSmallAircraft().getId() == 6 || sh.getSmallAircraft().getId() == 2;
    private Predicate<ShipHangar> notFightersAndGunBoats = sh -> sh.getSmallAircraft().getId() != 6 && sh.getSmallAircraft().getId() != 2;
    private Predicate<ShipLandingDeck> soldiersAndStormTroopers = ld -> ld.getLandForce().getId() == 1 || ld.getLandForce().getId() == 5;
    private Predicate<ShipLandingDeck> landingTech = ld -> ld.getLandForce().getId() != 1 && ld.getLandForce().getId() != 5 && ld.getLandForce().getId() != 8;
    private Predicate<ShipLandingDeck> landingBases = ld -> ld.getLandForce().getId() == 8;

    @Autowired
    private ShipService shipService;

    @Autowired
    private FleetService fleetService;

    @FXML
    public void initialize() {
        shipService.findAllShipsInShort().forEach(s -> {
            shipForComparison1.getItems().add(s);
            shipForComparison2.getItems().add(s);
            shipAndFleetUnitForComparison1.getItems().add(s);
        });
        Set<FleetUnitShort> units = fleetService.findAllFleetsInShort();
        units.stream()
                .sorted(Comparator.comparing(FleetUnitShort::level).reversed().thenComparing(FleetUnitShort::getName))
                .map(FleetUnitShort::getName)
                .forEach(unit -> {
                    fleetUnitForComparison1.getItems().add(unit);
                    fleetUnitForComparison2.getItems().add(unit);
                    shipAndFleetUnitForComparison2.getItems().add(unit);
                });

        shipCompare.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Optional<Ship> selectedShipOpt1 = shipService.getShipById(shipForComparison1.getSelectionModel().getSelectedItem());
            Optional<Ship> selectedShipOpt2 = shipService.getShipById(shipForComparison2.getSelectionModel().getSelectedItem());

            if (selectedShipOpt1.isPresent() && selectedShipOpt2.isPresent()) {
                Ship selectedShip1 = selectedShipOpt1.get();
                Ship selectedShip2 = selectedShipOpt2.get();
                StringBuilder shipComparisonResultBuilder = new StringBuilder();
                shipComparisonResultBuilder.append("<table><tr><th>Характеристика</th><th>Корабль 1</th><th>Корабль 2</th></tr>")
                        .append("<tr><td>Класс</td><td>").append(selectedShip1.getShipClass())
                        .append("</td><td>").append(selectedShip2.getShipClass()).append("</td></tr>")
                        .append("<tr><td>Тип</td><td>").append(selectedShip1.getType())
                        .append("</td><td>").append(selectedShip2.getType()).append("</td></tr>");

                shipComparisonResultBuilder.append(prepareReversedCompareRow("Стоимость",
                        selectedShip1.getCost(),
                        selectedShip2.getCost()));
                shipComparisonResultBuilder.append(prepareCompareRow("Автономность",
                        selectedShip1.getAutonomyInDays(),
                        selectedShip2.getAutonomyInDays()));
                shipComparisonResultBuilder.append(prepareCompareRow("Длина",
                        selectedShip1.getSize().getLength(),
                        selectedShip2.getSize().getLength()));
                shipComparisonResultBuilder.append(prepareCompareRow("Ширина",
                        selectedShip1.getSize().getWidth(),
                        selectedShip2.getSize().getWidth()));
                shipComparisonResultBuilder.append(prepareCompareRow("Высота",
                        selectedShip1.getSize().getHeight(),
                        selectedShip2.getSize().getHeight()));
                shipComparisonResultBuilder.append(prepareReversedCompareRow("Полноценный экипаж",
                        selectedShip1.getCrew().getNormal(),
                        selectedShip2.getCrew().getNormal()));
                shipComparisonResultBuilder.append(prepareReversedCompareRow("Минимальныф экипаж",
                        selectedShip1.getCrew().getMinimal(),
                        selectedShip2.getCrew().getMinimal()));
                shipComparisonResultBuilder.append(prepareCompareRow("Скорость в космосе",
                        selectedShip1.getSpeed().getSpaceSpeed(),
                        selectedShip2.getSpeed().getSpaceSpeed()));
                shipComparisonResultBuilder.append(prepareCompareRow("Скорость в Атмосфере",
                        selectedShip1.getSpeed().getAtmosphereSpeed(),
                        selectedShip2.getSpeed().getAtmosphereSpeed()));
                shipComparisonResultBuilder.append(prepareReversedCompareRow("Основной иппердвигатель",
                        selectedShip1.getSpeed().getMainHyperDriveClass(),
                        selectedShip2.getSpeed().getMainHyperDriveClass()));
                shipComparisonResultBuilder.append(prepareReversedCompareRow("Резервный иппердвигатель",
                        selectedShip1.getSpeed().getBackupHyperDriveClass(),
                        selectedShip2.getSpeed().getBackupHyperDriveClass()));
                shipComparisonResultBuilder.append(prepareCompareRow("Щиты",
                        selectedShip1.getDefence().getShields(),
                        selectedShip2.getDefence().getShields()));
                shipComparisonResultBuilder.append(prepareCompareRow("DR",
                        selectedShip1.getDefence().getDr(),
                        selectedShip2.getDefence().getDr()));
                shipComparisonResultBuilder.append(prepareCompareRow("HP",
                        selectedShip1.getDefence().getHp(),
                        selectedShip2.getDefence().getHp()));
                shipComparisonResultBuilder.append(prepareCompareRow("Порог повреждений",
                        selectedShip1.getDefence().getDamageThreshold(),
                        selectedShip2.getDefence().getDamageThreshold()));
                shipComparisonResultBuilder.append(prepareCompareRow("Количество орудий",
                        selectedShip1.getWeapons().stream().mapToInt(ShipWeapon::getWeaponCount).sum(),
                        selectedShip2.getWeapons().stream().mapToInt(ShipWeapon::getWeaponCount).sum()));
                shipComparisonResultBuilder.append(prepareCompareRow("Количество истребителей и канонерок",
                        selectedShip1.getHangar().stream()
                                .filter(fightersAndGunBoats)
                                .mapToInt(ShipHangar::getAircraftCount).sum(),
                        selectedShip2.getHangar().stream()
                                .filter(fightersAndGunBoats)
                                .mapToInt(ShipHangar::getAircraftCount).sum()));
                shipComparisonResultBuilder.append(prepareCompareRow("Количество прочих летательных аппаратов",
                        selectedShip1.getHangar().stream()
                                .filter(notFightersAndGunBoats)
                                .mapToInt(ShipHangar::getAircraftCount).sum(),
                        selectedShip2.getHangar().stream()
                                .filter(notFightersAndGunBoats)
                                .mapToInt(ShipHangar::getAircraftCount).sum()));
                shipComparisonResultBuilder.append(prepareCompareRow("Количество солдат или штурмовиков",
                        selectedShip1.getLandingDeck().stream()
                                .filter(soldiersAndStormTroopers)
                                .mapToInt(ShipLandingDeck::getLandForceCount).sum(),
                        selectedShip2.getLandingDeck().stream()
                                .filter(soldiersAndStormTroopers)
                                .mapToInt(ShipLandingDeck::getLandForceCount).sum()));
                shipComparisonResultBuilder.append(prepareCompareRow("Количество техники",
                        selectedShip1.getLandingDeck().stream()
                                .filter(landingTech)
                                .mapToInt(ShipLandingDeck::getLandForceCount).sum(),
                        selectedShip2.getLandingDeck().stream()
                                .filter(landingTech)
                                .mapToInt(ShipLandingDeck::getLandForceCount).sum()));
                shipComparisonResultBuilder.append(prepareCompareRow("Количество баз быстрой развертки",
                        selectedShip1.getLandingDeck().stream()
                                .filter(landingBases)
                                .mapToInt(ShipLandingDeck::getLandForceCount).sum(),
                        selectedShip2.getLandingDeck().stream()
                                .filter(landingBases)
                                .mapToInt(ShipLandingDeck::getLandForceCount).sum()));
                shipComparisonResultBuilder.append("</table>");
                shipComparisonResult.getEngine().loadContent(shipComparisonResultBuilder.toString());
            }
        });
        fleetUnitCompare.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            StringBuilder fleetUnitComparisonResultBuilder = new StringBuilder();
            String selection1 = fleetUnitForComparison1.getSelectionModel().getSelectedItem();
            String selection2 = fleetUnitForComparison2.getSelectionModel().getSelectedItem();
            if (selection1 != null && selection2 != null) {
                Optional<FleetUnitShort> selectedFleetUnitOpt1 = units.stream().filter(u -> u.getName().equals(selection1)).findAny();
                Optional<FleetUnitShort> selectedFleetUnitOpt2 = units.stream().filter(u -> u.getName().equals(selection2)).findAny();
                if (selectedFleetUnitOpt1.isPresent() && selectedFleetUnitOpt2.isPresent()) {
                    FleetUnitSummary summary1 = fleetService.prepareReportForFleetUnitById(selectedFleetUnitOpt1.get().getId());
                    FleetUnitSummary summary2 = fleetService.prepareReportForFleetUnitById(selectedFleetUnitOpt2.get().getId());
                    fleetUnitComparisonResultBuilder.append("<table><tr><th>Характеристика</th><th>Соединение 1</th><th>Соединение 2</th></tr>")
                            .append("<tr><td>Название</td><td>").append(selectedFleetUnitOpt1.get().getName())
                            .append("</td><td>").append(selectedFleetUnitOpt2.get().getName()).append("</td></tr>")
                            .append(prepareReversedCompareRow("Стоимость",
                                summary1.getCost(),
                                summary2.getCost()));
                    Optional<Integer> smallShips1 = summary1.getFilteredShipsCount(e -> e.getKey().getSize().getLength() < 500);
                    Optional<Integer> smallShips2 = summary1.getFilteredShipsCount(e -> e.getKey().getSize().getLength() < 500);
                    if (smallShips1.isPresent() && smallShips2.isPresent()) {
                        fleetUnitComparisonResultBuilder.append(prepareReversedCompareRow("Суда менее 500м",
                                smallShips1.get(),
                                smallShips2.get()));
                    }
                    Optional<Integer> mediumShips1 = summary1.getFilteredShipsCount(e -> e.getKey().getSize().getLength() >= 500 || e.getKey().getSize().getLength() < 1000);
                    Optional<Integer> mediumShips2 = summary1.getFilteredShipsCount(e -> e.getKey().getSize().getLength() >= 500 || e.getKey().getSize().getLength() < 1000);
                    if (mediumShips1.isPresent() && mediumShips2.isPresent()) {
                        fleetUnitComparisonResultBuilder.append(prepareReversedCompareRow("Суда от 500м до 1км",
                                mediumShips1.get(),
                                mediumShips2.get()));
                    }
                    Optional<Integer> largeShips1 = summary1.getFilteredShipsCount(e -> e.getKey().getSize().getLength() >= 1000);
                    Optional<Integer> largeShips2 = summary1.getFilteredShipsCount(e -> e.getKey().getSize().getLength() >= 1000);
                    if (largeShips1.isPresent() && largeShips2.isPresent()) {
                        fleetUnitComparisonResultBuilder.append(prepareReversedCompareRow("Суда более 1км",
                                largeShips1.get(),
                                largeShips2.get()));
                    }
                    fleetUnitComparisonResultBuilder.append(prepareCompareRow("Полноценный экипаж кораблей, пилоты и наземные силы",
                            summary1.getNormalShipsCrew() + summary1.getSmallAircraftCrew() + summary1.getLandingSoldiersAndCrew(),
                            summary2.getNormalShipsCrew() + summary2.getSmallAircraftCrew() + summary2.getLandingSoldiersAndCrew()));
                    fleetUnitComparisonResultBuilder.append(prepareCompareRow("Полноценный экипаж кораблей и пилоты",
                            summary1.getMinimalShipsCrew() + summary1.getSmallAircraftCrew(),
                            summary2.getMinimalShipsCrew() + summary2.getSmallAircraftCrew()));
                    fleetUnitComparisonResultBuilder.append(prepareCompareRow("Минмимальный экипаж кораблей без пилотов и сухопутных сил",
                            summary1.getMinimalShipsCrew(),
                            summary2.getMinimalShipsCrew()));
                    fleetUnitComparisonResultBuilder.append(prepareCompareRow("Только пилоты лётной палубы",
                            summary1.getSmallAircraftCrew(),
                            summary2.getSmallAircraftCrew()));
                    fleetUnitComparisonResultBuilder.append(prepareCompareRow("Только десант и наземные силы",
                            summary1.getLandingSoldiersAndCrew(),
                            summary2.getLandingSoldiersAndCrew()));
                    fleetUnitComparisonResultBuilder.append("</table>");
                }
            }
           fleetUnitComparisonResult.getEngine().loadContent(fleetUnitComparisonResultBuilder.toString());
        });
        shipAndFleetUnitCompare.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
           StringBuilder shipAndFleetUnitComparisonResultBuilder = new StringBuilder();
            String selection1 = shipAndFleetUnitForComparison2.getSelectionModel().getSelectedItem();
            if (selection1 != null) {
                Optional<Ship> selectedShipOpt1 = shipService.getShipById(shipAndFleetUnitForComparison1.getSelectionModel().getSelectedItem());
                if (selectedShipOpt1.isPresent()) {
                    Ship selectedShip = selectedShipOpt1.get();
                    Optional<FleetUnitShort> selectedFleetUnitOpt1 = units.stream().filter(u -> u.getName().equals(selection1)).findAny();
                    Optional<FleetUnit> selectedUnit = fleetService.getFleetUnitById(selectedFleetUnitOpt1.orElseThrow(() -> new IllegalArgumentException("Соединение флота не найдено")).getId());
                    selectedUnit.ifPresent(unit -> {
                        FleetUnitSummary summary = fleetService.prepareReportForFleetUnitById(unit.getId());
                        shipAndFleetUnitComparisonResultBuilder.append("<table><tr><th>Характеристика</th><th>Корабль</th><th>Соединение</th></tr>")
                                .append("<tr><td>Название</td><td>").append(selectedShip.getShipClass())
                                .append("</td><td>").append(selection1).append("</td></tr>")
                                .append(prepareReversedCompareRow("Стоимость",
                                        selectedShip.getCost(),
                                        summary.getCost()))
                                .append(prepareCompareRow("Экипаж кораблей без пилотов и наземных сил",
                                        new Long(selectedShip.getCrew().getNormal()),
                                        summary.getNormalShipsCrew()))
                                .append(prepareCompareRow("Прочие малые летательные аппараты",
                                        selectedShip.getHangar().stream()
                                                .filter(fightersAndGunBoats)
                                                .mapToInt(ShipHangar::getAircraftCount).sum(),
                                        summary.getSmallAircraftsWithCounts().entrySet().stream()
                                                .filter(e -> e.getKey().getId() == 6 || e.getKey().getId() == 2).mapToInt(Map.Entry::getValue).sum()))
                                .append(prepareCompareRow("Прочие малые летательные аппараты",
                                        selectedShip.getHangar().stream()
                                                .filter(notFightersAndGunBoats)
                                                .mapToInt(ShipHangar::getAircraftCount).sum(),
                                        summary.getSmallAircraftsWithCounts().entrySet().stream()
                                                .filter(e -> e.getKey().getId() != 6 && e.getKey().getId() != 2).mapToInt(Map.Entry::getValue).sum()))
                                .append(prepareCompareRow("Солдаты и штурмовики",
                                        selectedShip.getLandingDeck().stream()
                                                .filter(soldiersAndStormTroopers)
                                                .mapToInt(ShipLandingDeck::getLandForceCount).sum(),
                                        summary.getLandForcesWithCounts().entrySet().stream()
                                                .filter(ld -> ld.getKey().getId() == 1 || ld.getKey().getId() == 5).mapToInt(Map.Entry::getValue).sum()))
                                .append(prepareCompareRow("Наземная техника",
                                        selectedShip.getLandingDeck().stream()
                                                .filter(landingTech)
                                                .mapToInt(ShipLandingDeck::getLandForceCount).sum(),
                                        summary.getLandForcesWithCounts().entrySet().stream()
                                                .filter(ld -> ld.getKey().getId() != 1 && ld.getKey().getId() != 5 && ld.getKey().getId() != 8).mapToInt(Map.Entry::getValue).sum()))
                                .append(prepareCompareRow("Базы быстрой развертки",
                                        selectedShip.getLandingDeck().stream()
                                                .filter(landingBases)
                                                .mapToInt(ShipLandingDeck::getLandForceCount).sum(),
                                        summary.getLandForcesWithCounts().entrySet().stream()
                                                .filter(ld -> ld.getKey().getId() == 8).mapToInt(Map.Entry::getValue).sum()));
                        shipAndFleetUnitComparisonResultBuilder.append("</table>");
                    });
                }
            }
           shipAndFleetUnitComparisonResult.getEngine().loadContent(shipAndFleetUnitComparisonResultBuilder.toString());
        });
    }

    private String prepareCompareRow(String name, Comparable number1, Comparable number2) {
        return prepareRowWithPredicates(name, number1, number2,
                (n1,n2) -> n1.compareTo(n2) > 0,
                (n1,n2) -> n1.compareTo(n2) < 0);
    }

    private String prepareReversedCompareRow(String name, Comparable number1, Comparable number2) {
        return prepareRowWithPredicates(name, number1, number2,
                (n1,n2) -> n1.compareTo(n2) < 0,
                (n1,n2) -> n1.compareTo(n2) > 0);
    }

    private String prepareRowWithPredicates(String name, Comparable number1, Comparable number2,
                                            BiPredicate<Comparable, Comparable> firstCondition,
                                            BiPredicate<Comparable, Comparable> secondCondition) {
        StringBuilder builder = new StringBuilder();
        if (number1 == null && number2 == null) {
            return "";
        } else if  (number1 != null && number2 != null) {
            builder.append("<tr><td>").append(name).append("</td>");
            if (firstCondition.test(number1, number2)) {
                builder.append("<td style=\"color: green;\">").append(number1).append("</td>")
                        .append("<td style=\"color: red;\">").append(number2).append("</td>");
            } else if (secondCondition.test(number1, number2)) {
                builder.append("<td style=\"color: red;\">").append(number1).append("</td>")
                        .append("<td style=\"color: green;\">").append(number2).append("</td>");
            } else {
                builder.append("<td>").append(number1).append("</td>")
                        .append("<td>").append(number2).append("</td>");
            }
            builder.append("</tr>");
        } else {
            builder.append("<tr><td>").append(name).append("</td>");
            builder.append("<td>").append(number1).append("</td>")
                    .append("<td>").append(number2).append("</td>");
        }
        return builder.toString();
    }
}
