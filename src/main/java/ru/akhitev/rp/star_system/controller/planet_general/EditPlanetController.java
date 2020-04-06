package ru.akhitev.rp.star_system.controller.planet_general;

import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import ru.akhitev.rp.star_system.entity.Planet;
import ru.akhitev.rp.star_system.entity.StarSystem;

public class EditPlanetController extends AbstractPlanetGeneralDialog {
    private static final String QUERY_TEMPLATE = "UPDATE planet SET name = '%s', population = %s WHERE id = %s;";
    private Logger logger = LoggerFactory.getLogger(EditPlanetController.class);
    private Planet planet;

    public EditPlanetController(Planet planet, StarSystem starSystem, ApplicationContext context) {
        super(starSystem, context);
        this.planet = planet;
    }

    @FXML
    public void initialize() {
        name.setText(planet.getName());
        population.setText(String.valueOf(planet.getPopulation()));
        save.setOnMouseClicked(event -> onSave(planet, QUERY_TEMPLATE, event));
        next.setOnMouseClicked(event -> onNext(planet, QUERY_TEMPLATE, event));
    }

    @Override
    protected Logger logger() {
        return logger;
    }
}
