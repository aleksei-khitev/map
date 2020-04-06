package ru.akhitev.rp.star_system.controller.planet_general;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import ru.akhitev.rp.resource.entity.CriticalResource;
import ru.akhitev.rp.resource.repo.CriticalResourceRepo;
import ru.akhitev.rp.star_system.controller.AbstractDialog;
import ru.akhitev.rp.star_system.controller.EditPlanetResourcesDialog;
import ru.akhitev.rp.star_system.entity.Planet;
import ru.akhitev.rp.star_system.entity.PlanetResource;
import ru.akhitev.rp.star_system.entity.StarSystem;
import ru.akhitev.rp.star_system.repo.PlanetMiningRepo;
import ru.akhitev.rp.star_system.repo.PlanetProductionRepo;
import ru.akhitev.rp.star_system.repo.PlanetRepo;
import ru.akhitev.rp.star_system.repo.PlanetResourceRepo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

abstract class AbstractPlanetGeneralDialog extends AbstractDialog {
    @FXML TextField name;
    @FXML TextField population;
    @FXML ComboBox<CriticalResource> resourceComboBox;
    @FXML TextField resourceAmount;
    @FXML Button save;
    @FXML Button next;
    ApplicationContext context;
    PlanetRepo planetRepo;
    PlanetResourceRepo planetResourceRepo;
    PlanetMiningRepo planetMiningRepo;
    PlanetProductionRepo planetProductionRepo;
    CriticalResourceRepo criticalResourceRepo;
    StarSystem starSystem;

    public AbstractPlanetGeneralDialog(StarSystem starSystem, ApplicationContext context) {
        this.planetRepo = context.getBean(PlanetRepo.class);
        this.planetResourceRepo = context.getBean(PlanetResourceRepo.class);
        this.planetMiningRepo = context.getBean(PlanetMiningRepo.class);
        this.planetProductionRepo = context.getBean(PlanetProductionRepo.class);
        this.criticalResourceRepo = context.getBean(CriticalResourceRepo.class);
        this.starSystem = starSystem;
        this.context = context;
    }

    Planet save(Planet planet) {
        planet.setName(name.getText());
        planet.setStarSystem(starSystem);
        planet.setPopulation(Long.valueOf(population.getText()));
        return planetRepo.save(planet);
    }

    void printQuery(Planet planet, String template) {
        try (FileWriter fileWriter = new FileWriter("queries_history.sql", true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            String query = String.format(template,
                    planet.getId(),
                    planet.getStarSystem().getId(),
                    planet.getName(),
                    planet.getPopulation());
            printWriter.println(query);
            logger().info("Запрос {} успешно сохранен в файл", query);
        } catch (IOException e) {
            logger().error("Ошибка при сохранении запроса в файл", e);
        }
    }

    void onSave(Planet planet, String template, MouseEvent event) {
        printQuery(planet, template);
        Node source = (Node) event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    void onNext(Planet planet, String template, MouseEvent event) {
        planet = save(planet);
        printQuery(planet, template);
        Node source = (Node) event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        Scene scene = stage.getScene();
        openResourceDialog(scene, planet);
    }

    void openResourceDialog(Scene scene, Planet planet) {
        scene = openDialog(scene, new EditPlanetResourcesDialog(planet, context),"/ru/akhitev/rp/star_system/editPlanet_resources_productions.fxml");
        scene.getWindow().setWidth(800);
        scene.getWindow().setHeight(400);
    }
}
