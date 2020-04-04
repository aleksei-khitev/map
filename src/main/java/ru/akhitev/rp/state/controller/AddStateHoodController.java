package ru.akhitev.rp.state.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.akhitev.rp.map.entity.StateHood;
import ru.akhitev.rp.map.repository.StateHoodRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AddStateHoodController {
    private static Logger logger = LoggerFactory.getLogger(AddStateHoodController.class);
    private static final String QUERY_TEMPLATE = "INSERT INTO State_Hood(id, name, color) VALUES (%s,'%s', '%s');";
    @FXML private TextField name;
    @FXML private ColorPicker color;
    @FXML private Button save;
    private StateHoodRepository stateHoodRepository;

    @Autowired
    public AddStateHoodController(StateHoodRepository stateHoodRepository) {
        this.stateHoodRepository = stateHoodRepository;
    }

    @FXML
    public void initialize() {
        save.setOnMouseClicked((actionEvent) -> {
            StateHood stateHood = new StateHood();
            stateHood.setName(name.getText());
            stateHood.setColor(color.getValue().toString());
            stateHoodRepository.save(stateHood);
            try (FileWriter fileWriter = new FileWriter("queries_history.sql", true);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                String query = String.format(QUERY_TEMPLATE,
                        stateHood.getId(),
                        stateHood.getName(),
                        stateHood.getColor());
                printWriter.println(query);
                Node source = (Node) actionEvent.getSource();
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                logger.error("Ошибка при сохранении запроса в файл", e);
            }
        });
    }
}
