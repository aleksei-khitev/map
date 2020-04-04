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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

class EditSystemDialogController extends AbstractController {
    private static Logger logger = LoggerFactory.getLogger(EditSystemDialogController.class);
    private static final String QUERY_TEMPLATE = "UPDATE Star_System SET name = '%s', statehood_id = %s, super_statehood_id = %s, system_importance = %s, coordinateX = %s, coordinateY = %s WHERE id = %s";
    @FXML private TextField name;
    @FXML private ComboBox<StateHood> stateHood;
    @FXML private ComboBox<SuperStateHood> superStateHood;
    @FXML private ComboBox<String> importance;
    @FXML private TextField coordinates;
    @FXML private Button save;
    private StarSystem starSystem;
    private StarSystemRepository starSystemRepository;
    private StateHoodRepository stateHoodRepository;
    private SuperStateHoodRepository superStateHoodRepository;

    EditSystemDialogController(StarSystem starSystem, StarSystemRepository starSystemRepository, StateHoodRepository stateHoodRepository, SuperStateHoodRepository superStateHoodRepository) {
        this.starSystem = starSystem;
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
            try (FileWriter fileWriter = new FileWriter("queries_history.sql", true);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                String query = String.format(QUERY_TEMPLATE,
                        starSystem.getName(),
                        starSystem.getStatehood().getId(),
                        starSystem.getSuperStatehood().getId(),
                        importanceValue,
                        starSystem.getCoordinateX(),
                        starSystem.getCoordinateY(),
                        starSystem.getId());
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
