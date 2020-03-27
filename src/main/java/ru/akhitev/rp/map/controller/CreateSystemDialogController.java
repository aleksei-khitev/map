package ru.akhitev.rp.map.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.akhitev.rp.conf.AbstractController;
import ru.akhitev.rp.map.entity.StarSystem;
import ru.akhitev.rp.map.entity.StateHood;
import ru.akhitev.rp.map.entity.SuperStateHood;
import ru.akhitev.rp.map.repository.StarSystemRepository;
import ru.akhitev.rp.map.repository.StateHoodRepository;
import ru.akhitev.rp.map.repository.SuperStateHoodRepository;

import java.io.*;

class CreateSystemDialogController extends AbstractController {
    private static Logger logger = LoggerFactory.getLogger(CreateSystemDialogController.class);
    private static final String QUERY_TEMPLATE = "INSERT INTO Star_System(id, name, statehood_id, super_statehood_id, system_importance, coordinateX, coordinateY) VALUES (%s, '%s', %s, %s, %s, %s, %s);";
    @FXML private TextField name;
    @FXML private ComboBox<StateHood> stateHood;
    @FXML private ComboBox<SuperStateHood> superStateHood;
    @FXML private TextField importance;
    @FXML private TextField coordinates;
    @FXML private Button save;
    private double contextX;
    private double contextY;
    private StarSystemRepository starSystemRepository;
    private StateHoodRepository stateHoodRepository;
    private SuperStateHoodRepository superStateHoodRepository;

    CreateSystemDialogController(double contextX, double contextY, StarSystemRepository starSystemRepository, StateHoodRepository stateHoodRepository, SuperStateHoodRepository superStateHoodRepository) {
        this.contextX = contextX;
        this.contextY = contextY;
        this.starSystemRepository = starSystemRepository;
        this.stateHoodRepository = stateHoodRepository;
        this.superStateHoodRepository = superStateHoodRepository;
    }

    @FXML
    public void initialize() {
        for (StateHood hood : stateHoodRepository.findAll()) {
            stateHood.getItems().add(hood);
        }
        for (SuperStateHood superHood : superStateHoodRepository.findAll()) {
            superStateHood.getItems().add(superHood);
        }
        coordinates.setText(contextX + " x " + contextY);
        save.setOnAction((actionEvent) -> {
            StarSystem starSystem = new StarSystem();
            starSystem.setName(name.getText());
            starSystem.setStatehood(stateHood.getSelectionModel().getSelectedItem());
            starSystem.setSuperStatehood(superStateHood.getSelectionModel().getSelectedItem());
            starSystem.setSystemImportance(StarSystem.StarSystemImportance.byId(Integer.valueOf(importance.getText())));
            starSystem.setCoordinateX(contextX);
            starSystem.setCoordinateY(contextY);
            starSystem = starSystemRepository.save(starSystem);
            try (FileWriter fileWriter = new FileWriter("queries_history.sql", true);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                String query = String.format(QUERY_TEMPLATE,
                        starSystem.getId(),
                        starSystem.getName(),
                        starSystem.getStatehood().getId(),
                        starSystem.getSuperStatehood().getId(),
                        importance.getText(),
                        starSystem.getCoordinateX(),
                        starSystem.getCoordinateY());
                printWriter.println(query);
                Node source = (Node) actionEvent.getSource();
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
                logger.info("Запрос {} успешно сохранен в файл", query);
            } catch (IOException e) {
                logger.error("Ошибка при сохранении запроса в файл", e);
            }
        });
    }
}
