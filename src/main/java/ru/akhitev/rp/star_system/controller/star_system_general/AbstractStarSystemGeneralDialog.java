package ru.akhitev.rp.star_system.controller.star_system_general;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import ru.akhitev.rp.star_system.controller.AbstractDialog;
import ru.akhitev.rp.star_system.controller.EditStarSystemsPlanetsController;
import ru.akhitev.rp.star_system.entity.StarSystem;
import ru.akhitev.rp.star_system.repo.StarSystemRepository;
import ru.akhitev.rp.state_hood.entity.StateHood;
import ru.akhitev.rp.state_hood.repo.StateHoodRepository;
import ru.akhitev.rp.super_state_hood.entity.SuperStateHood;
import ru.akhitev.rp.super_state_hood.repo.SuperStateHoodRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

abstract class AbstractStarSystemGeneralDialog extends AbstractDialog {
    @FXML TextField name;
    @FXML ComboBox<StateHood> stateHood;
    @FXML ComboBox<SuperStateHood> superStateHood;
    @FXML ComboBox<String> importance;
    @FXML TextField coordinates;
    @FXML Button save;
    StarSystemRepository starSystemRepository;
    StateHoodRepository stateHoodRepository;
    SuperStateHoodRepository superStateHoodRepository;
    ApplicationContext context;

    public AbstractStarSystemGeneralDialog(ApplicationContext context) {
        this.context = context;
        this.starSystemRepository = context.getBean(StarSystemRepository.class);
        this.stateHoodRepository = context.getBean(StateHoodRepository.class);
        this.superStateHoodRepository = context.getBean(SuperStateHoodRepository.class);
    }

    protected void openPlanetsDialog(Scene scene, StarSystem starSystem) {
        openDialog(scene, new EditStarSystemsPlanetsController(context, starSystem), "/ru/akhitev/rp/star_system/addEditSystemDialog_planets.fxml");
    }

    void printQuery(StarSystem starSystem, int importanceValue, String template) {
        try (FileWriter fileWriter = new FileWriter("queries_history.sql", true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            String query = String.format(template,
                    starSystem.getName(),
                    starSystem.getStatehood().getId(),
                    ((starSystem.getSuperStatehood() != null)?starSystem.getSuperStatehood().getId():null),
                    importanceValue,
                    starSystem.getCoordinateX(),
                    starSystem.getCoordinateY(),
                    starSystem.getId());
            printWriter.println(query);
            logger().info("Запрос {} успешно сохранен в файл", query);
        } catch (IOException e) {
            logger().error("Ошибка при сохранении запроса в файл", e);
        }
    }
}
