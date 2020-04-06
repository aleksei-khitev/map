package ru.akhitev.rp.star_system.controller.planet_general;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import ru.akhitev.rp.star_system.entity.Planet;
import ru.akhitev.rp.star_system.entity.StarSystem;

public class AddPlanetController extends AbstractPlanetGeneralDialog {
    private static final String QUERY_TEMPLATE = "INSERT INTO planet(id, star_system_id, name, population) VALUES (%s, %s, '%s', %s);";
    private static Logger logger = LoggerFactory.getLogger(AddPlanetController.class);

    public AddPlanetController(StarSystem starSystem, ApplicationContext context) {
        super(starSystem, context);
    }

    @FXML
    public void initialize() {
        name.setText(starSystem.getName());
        save.setOnMouseClicked(event -> onSave(new Planet(), QUERY_TEMPLATE, event));
        next.setOnMouseClicked(event -> onNext(new Planet(), QUERY_TEMPLATE, event));
    }

    @Override
    protected Logger logger() {
        return logger;
    }
}
