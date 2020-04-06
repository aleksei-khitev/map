package ru.akhitev.rp.star_system.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import ru.akhitev.rp.star_system.controller.planet_general.AddPlanetController;
import ru.akhitev.rp.star_system.controller.planet_general.EditPlanetController;
import ru.akhitev.rp.star_system.entity.Planet;
import ru.akhitev.rp.star_system.entity.StarSystem;
import ru.akhitev.rp.star_system.repo.StarSystemRepository;

import java.io.IOException;
import java.net.URL;

public class EditStarSystemsPlanetsController {
    private static Logger logger = LoggerFactory.getLogger(EditStarSystemsPlanetsController.class);
    @FXML private Button addPlanet;
    @FXML private ListView<Planet> planets;
    @FXML private Button next;
    private ApplicationContext context;
    private StarSystem starSystem;

    public EditStarSystemsPlanetsController(ApplicationContext context, StarSystem starSystem) {
        this.context = context;
        this.starSystem = starSystem;
    }

    @FXML
    public void initialize() {
        refreshPlanetList();
        addPlanet.setOnMouseClicked(event -> {
            editPlanet(new AddPlanetController(starSystem, context));
        });
        planets.setOnMouseClicked(event -> {
            editPlanet(new EditPlanetController(planets.getSelectionModel().getSelectedItem(), starSystem, context));
        });
    }

    private void editPlanet(Object controller) {
        URL resource = getClass().getResource("/ru/akhitev/rp/star_system/addEditPlanet_general.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        loader.setController(controller);
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            logger.error("Ошибка при открытии диалога", e);
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Добавление планеты");
        stage.showAndWait();
        refreshPlanetList();
    }

    private void refreshPlanetList() {
        StarSystemRepository starSystemRepo = context.getBean(StarSystemRepository.class);
        starSystemRepo.refresh(starSystem);
        starSystem = starSystemRepo.findById(starSystem.getId()).get();
        planets.getItems().clear();
        if (starSystem.getPlanets() != null) {
            for (Planet planet : starSystem.getPlanets()) {
                planets.getItems().add(planet);
            }
        }
    }
}
