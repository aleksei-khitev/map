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

public class EditSystemDialogController extends AbstractStarSystemGeneralDialog {
    private static Logger logger = LoggerFactory.getLogger(EditSystemDialogController.class);
    private static final String QUERY_TEMPLATE = "UPDATE Star_System SET name = '%s', statehood_id = %s, super_statehood_id = %s, system_importance = %s, coordinateX = %s, coordinateY = %s WHERE id = %s";
    private StarSystem starSystem;

    public EditSystemDialogController(StarSystem starSystem, ApplicationContext context) {
        super(context);
        this.starSystem = starSystem;
    }

    @FXML
    public void initialize() {
        for (StateHood hood : stateHoodRepository.findAll()) {
            stateHood.getItems().add(hood);
        }
        for (SuperStateHood superHood : superStateHoodRepository.findAll()) {
            superStateHood.getItems().add(superHood);
        }
        name.setText(starSystem.getName());
        Arrays.stream(StarSystem.StarSystemImportance.values())
                .map(StarSystem.StarSystemImportance::getName)
                .forEach(importance.getItems()::add);
        importance.getSelectionModel().select(starSystem.getSystemImportance().getName());
        stateHood.getSelectionModel().select(starSystem.getStatehood());
        superStateHood.getSelectionModel().select(starSystem.getSuperStatehood());
        coordinates.setText(starSystem.getCoordinateX() + " x " + starSystem.getCoordinateY());
        save.setOnAction((actionEvent) -> {
            int importanceValue = StarSystem.StarSystemImportance.byName(importance.getSelectionModel().getSelectedItem()).getId();
            starSystem.setName(name.getText());
            starSystem.setStatehood(stateHood.getSelectionModel().getSelectedItem());
            starSystem.setSuperStatehood(superStateHood.getSelectionModel().getSelectedItem());
            starSystem.setSystemImportance(StarSystem.StarSystemImportance.byId(importanceValue));
            starSystem = starSystemRepository.save(starSystem);
            printQuery(starSystem, importanceValue, QUERY_TEMPLATE);
            Node source = (Node) actionEvent.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            Scene scene = stage.getScene();
            openPlanetsDialog(scene, starSystem);
        });
    }

    protected Logger logger() {
        return logger;
    }
}
