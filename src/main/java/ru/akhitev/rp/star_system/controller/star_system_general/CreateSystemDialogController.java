package ru.akhitev.rp.star_system.controller.star_system_general;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import ru.akhitev.rp.star_system.entity.StarSystem;
import ru.akhitev.rp.state_hood.entity.StateHood;
import ru.akhitev.rp.super_state_hood.entity.SuperStateHood;

import java.util.Arrays;

public class CreateSystemDialogController extends AbstractStarSystemGeneralDialog {
    private static Logger logger = LoggerFactory.getLogger(CreateSystemDialogController.class);
    private static final String QUERY_TEMPLATE = "INSERT INTO Star_System(id, name, statehood_id, super_statehood_id, system_importance, coordinateX, coordinateY) VALUES (%s, '%s', %s, %s, %s, %s, %s);";
    private double contextX;
    private double contextY;

    public CreateSystemDialogController(double contextX, double contextY, ApplicationContext context) {
        super(context);
        this.contextX = contextX;
        this.contextY = contextY;
    }

    @FXML
    public void initialize() {
        for (StateHood hood : stateHoodRepository.findAll()) {
            stateHood.getItems().add(hood);
        }
        for (SuperStateHood superHood : superStateHoodRepository.findAll()) {
            superStateHood.getItems().add(superHood);
        }
        Arrays.stream(StarSystem.StarSystemImportance.values())
                .map(StarSystem.StarSystemImportance::getName)
                .forEach(importance.getItems()::add);
        coordinates.setText(contextX + " x " + contextY);
        save.setOnAction((actionEvent) -> {
            int importanceValue = StarSystem.StarSystemImportance.byName(importance.getSelectionModel().getSelectedItem()).getId();
            StarSystem starSystem = new StarSystem();
            starSystem.setName(name.getText());
            starSystem.setStatehood(stateHood.getSelectionModel().getSelectedItem());
            starSystem.setSuperStatehood(superStateHood.getSelectionModel().getSelectedItem());
            starSystem.setSystemImportance(StarSystem.StarSystemImportance.byId(importanceValue));
            starSystem.setCoordinateX(contextX);
            starSystem.setCoordinateY(contextY);
            starSystem = starSystemRepository.save(starSystem);
            printQuery(starSystem, importanceValue, QUERY_TEMPLATE);
            Node source = (Node) actionEvent.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            Scene scene = stage.getScene();
            openPlanetsDialog(scene, starSystem);
        });
    }

    @Override
    protected Logger logger() {
        return logger;
    }
}
