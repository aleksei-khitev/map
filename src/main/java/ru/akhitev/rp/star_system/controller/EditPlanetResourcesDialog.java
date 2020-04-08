package ru.akhitev.rp.star_system.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import ru.akhitev.rp.production.entity.CriticalProduction;
import ru.akhitev.rp.production.repo.CriticalProductionRepo;
import ru.akhitev.rp.resource.entity.CriticalResource;
import ru.akhitev.rp.resource.repo.CriticalResourceRepo;
import ru.akhitev.rp.star_system.entity.Planet;
import ru.akhitev.rp.star_system.entity.PlanetMining;
import ru.akhitev.rp.star_system.entity.PlanetProduction;
import ru.akhitev.rp.star_system.entity.PlanetResource;
import ru.akhitev.rp.star_system.repo.PlanetMiningRepo;
import ru.akhitev.rp.star_system.repo.PlanetProductionRepo;
import ru.akhitev.rp.star_system.repo.PlanetRepo;
import ru.akhitev.rp.star_system.repo.PlanetResourceRepo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class EditPlanetResourcesDialog {
    private static final String ADD_PLANET_RESOURCE_QUERY = "INSERT INTO planet_resource(id, planet_id, critical_resource_id, amount) VALUES (%s, %s, %s, %s);";
    private static final String ADD_PLANET_MINING_QUERY = "INSERT INTO planet_resource_mining(id, planet_id, critical_resource_id, amount_per_year) VALUES (%s, %s, %s, %s);";
    private static final String ADD_PLANET_PRODUCTION_QUERY = "INSERT INTO planet_production(id, planet_id, critical_production_id, level) VALUES (%s, %s, %s, %s);";
    private static Logger logger = LoggerFactory.getLogger(EditPlanetResourcesDialog.class);
    @FXML private ComboBox<CriticalResource> resourceComboBox;
    @FXML private ComboBox<String> resourceAmount;
    @FXML private Button addResourceButton;
    @FXML private ListView<PlanetResource> planetResources;

    @FXML private ComboBox<PlanetResource> planetResourceComboBox;
    @FXML private ComboBox<String> resourceAmountPerYear;
    @FXML private Button addResourceMiningButton;
    @FXML private ListView<PlanetMining> planetMining;

    @FXML private ComboBox<CriticalProduction> productionComboBox;
    @FXML private ComboBox<String> productionAmountPerYear;
    @FXML private Button addProductionButton;
    @FXML private ListView<PlanetProduction> planetProduction;

    private Planet planet;
    private ApplicationContext context;
    private PlanetRepo planetRepo;
    private CriticalResourceRepo criticalResourceRepo;
    private PlanetResourceRepo planetResourceRepo;
    private PlanetMiningRepo planetMiningRepo;
    private CriticalProductionRepo criticalProductionRepo;
    private PlanetProductionRepo planetProductionRepo;

    public EditPlanetResourcesDialog(Planet planet, ApplicationContext context) {
        this.planet = planet;
        this.context = context;
        planetRepo = context.getBean(PlanetRepo.class);
        criticalResourceRepo = context.getBean(CriticalResourceRepo.class);
        planetResourceRepo = context.getBean(PlanetResourceRepo.class);
        planetMiningRepo = context.getBean(PlanetMiningRepo.class);
        criticalProductionRepo = context.getBean(CriticalProductionRepo.class);
        planetProductionRepo = context.getBean(PlanetProductionRepo.class);
    }

    @FXML
    public void initialize() {
        for (CriticalResource resource : criticalResourceRepo.findAll()) {
            resourceComboBox.getItems().add(resource);
        }
        for (CriticalProduction production : criticalProductionRepo.findAll()) {
            productionComboBox.getItems().add(production);
        }
        reloadLists();
        addResourceButton.setOnMouseClicked(event -> {
            PlanetResource resource = new PlanetResource();
            resource.setPlanet(planet);
            resource.setCriticalResource(resourceComboBox.getSelectionModel().getSelectedItem());
            resource.setAmount(calculateOverallAmount());
            resource = planetResourceRepo.save(resource);
            String query = String.format(ADD_PLANET_RESOURCE_QUERY,
                    resource.getId(), resource.getPlanet().getId(),
                    resource.getCriticalResource().getId(),
                    resource.getAmount());
            printQuery(query);
            reloadLists();
        });
        addResourceMiningButton.setOnMouseClicked(event -> {
            PlanetMining mining = new PlanetMining();
            mining.setPlanet(planet);
            mining.setCriticalResource(planetResourceComboBox.getSelectionModel().getSelectedItem().getCriticalResource());
            mining.setAmountPerYear(calculatePerYearAmount());
            mining = planetMiningRepo.save(mining);
            String query = String.format(ADD_PLANET_MINING_QUERY,
                    mining.getId(), mining.getPlanet().getId(),
                    mining.getCriticalResource().getId(),
                    mining.getAmountPerYear());
            printQuery(query);
            reloadLists();
        });
        addProductionButton.setOnMouseClicked(event -> {
            PlanetProduction production = new PlanetProduction();
            production.setPlanet(planet);
            production.setCriticalProduction(productionComboBox.getSelectionModel().getSelectedItem());
            production.setLevel(Integer.valueOf(productionAmountPerYear.getSelectionModel().getSelectedItem()));
            production = planetProductionRepo.save(production);
            String query = String.format(ADD_PLANET_PRODUCTION_QUERY,
                    production.getId(), production.getPlanet().getId(),
                    production.getCriticalProduction().getId(),
                    production.getLevel());
            printQuery(query);
            reloadLists();
        });
    }

    private void reloadLists() {
        planetResources.getItems().clear();
        planetResourceComboBox.getItems().clear();
        planetMining.getItems().clear();
        planetProduction.getItems().clear();
        planetRepo.refresh(planet);
        planet = planetRepo.findById(planet.getId()).get();
        if (planet.getPlanetResources() != null) {
            for (PlanetResource resource : planet.getPlanetResources()) {
                planetResources.getItems().add(resource);
                planetResourceComboBox.getItems().add(resource);
            }
        }
        if (planet.getPlanetMinings() != null) {
            for (PlanetMining mining : planet.getPlanetMinings()) {
                planetMining.getItems().add(mining);
            }
        }
        if (planet.getPlanetProductions() != null) {
            for (PlanetProduction production : planet.getPlanetProductions()) {
                planetProduction.getItems().add(production);
            }
        }
    }

    private long calculateOverallAmount() {
        switch (resourceComboBox.getSelectionModel().getSelectedItem().getName()) {
            case "Зерсиумная руда":
                return processAmountWithBorders(5000, 10000);
            case "Кристаллы Нова" :
                return processAmountWithBorders(1000, 9000);
            default:
                return processAmountWithBorders(10000, 20000);
        }
    }

    private long calculatePerYearAmount() {
        switch (resourceComboBox.getSelectionModel().getSelectedItem().getName()) {
            case "Зерсиумная руда":
                return processAmountWithBorders(500, 1000);
            case "Кристаллы Нова" :
                return processAmountWithBorders(100, 900);
            default:
                return processAmountWithBorders(1000, 2000);
        }
    }

    private long processAmountWithBorders(long bottom, long top) {
        long min;
        long max;
        switch (resourceAmount.getSelectionModel().getSelectedItem()) {
            case "Гигантские" :
                min = top * 3;
                max = top * 4;
                break;
            case "Богатые":
                min = top * 2;
                max = top * 3;
                break;
            case "Обычные":
                min = top;
                max = top * 2;
                break;
            default:
                min = bottom;
                max = top;
        }
        return (new Random().nextLong() % (max - min)) + min;
    }

    void printQuery(String query) {
        try (FileWriter fileWriter = new FileWriter("queries_history.sql", true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(query);
            logger.info("Запрос {} успешно сохранен в файл", query);
        } catch (IOException e) {
            logger.error("Ошибка при сохранении запроса в файл", e);
        }
    }
}
