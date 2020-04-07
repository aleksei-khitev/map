package ru.akhitev.rp.star_system.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.context.ApplicationContext;
import ru.akhitev.rp.resource.entity.CriticalResource;
import ru.akhitev.rp.resource.repo.CriticalResourceRepo;
import ru.akhitev.rp.star_system.entity.Planet;
import ru.akhitev.rp.star_system.entity.PlanetMining;
import ru.akhitev.rp.star_system.entity.PlanetResource;
import ru.akhitev.rp.star_system.repo.PlanetMiningRepo;
import ru.akhitev.rp.star_system.repo.PlanetRepo;
import ru.akhitev.rp.star_system.repo.PlanetResourceRepo;

import java.util.Random;

public class EditPlanetResourcesDialog {
    @FXML private ComboBox<CriticalResource> resourceComboBox;
    @FXML private ComboBox<String> resourceAmount;
    @FXML private Button addResourceButton;
    @FXML private ListView<PlanetResource> planetResources;

    @FXML private ComboBox<PlanetResource> planetResourceComboBox;
    @FXML private ComboBox<String> resourceAmountPerYear;
    @FXML private Button addResourceMiningButton;
    @FXML private ListView<PlanetMining> planetMining;

    private Planet planet;
    private ApplicationContext context;
    private PlanetRepo planetRepo;
    private CriticalResourceRepo criticalResourceRepo;
    private PlanetResourceRepo planetResourceRepo;
    private PlanetMiningRepo planetMiningRepo;

    public EditPlanetResourcesDialog(Planet planet, ApplicationContext context) {
        this.planet = planet;
        this.context = context;
        planetRepo = context.getBean(PlanetRepo.class);
        criticalResourceRepo = context.getBean(CriticalResourceRepo.class);
        planetResourceRepo = context.getBean(PlanetResourceRepo.class);
        planetMiningRepo = context.getBean(PlanetMiningRepo.class);
    }

    @FXML
    public void initialize() {
        for (CriticalResource resource : criticalResourceRepo.findAll()) {
            resourceComboBox.getItems().add(resource);
        }
        reloadLists();
        addResourceButton.setOnMouseClicked(event -> {
            PlanetResource resource = new PlanetResource();
            resource.setPlanet(planet);
            resource.setCriticalResource(resourceComboBox.getSelectionModel().getSelectedItem());
            resource.setAmount(calculateOverallAmount());
            resource = planetResourceRepo.save(resource);
            reloadLists();
        });
        addResourceMiningButton.setOnMouseClicked(event -> {
            PlanetMining mining = new PlanetMining();
            mining.setPlanet(planet);
            mining.setCriticalResource(planetResourceComboBox.getSelectionModel().getSelectedItem().getCriticalResource());
            mining.setAmountPerYear(calculatePerYearAmount());
            planetMiningRepo.save(mining);
            reloadLists();
        });
    }

    private void reloadLists() {
        planetResources.getItems().clear();
        planetResourceComboBox.getItems().clear();
        planetMining.getItems().clear();
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
}
