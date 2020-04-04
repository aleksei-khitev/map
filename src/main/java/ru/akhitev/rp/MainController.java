package ru.akhitev.rp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.akhitev.rp.conf.AbstractController;
import ru.akhitev.rp.map.repository.StateHoodRepository;
import ru.akhitev.rp.state.controller.AddStateHoodController;

import java.io.IOException;
import java.net.URL;

@Component
public class MainController extends AbstractController {
    private static Logger logger = LoggerFactory.getLogger(MainController.class);
    @FXML private MenuItem addState;
    @FXML private MenuItem addSuperState;

    @FXML
    public void initialize() {
        addState.setOnAction((actionEvent) -> {
            AddStateHoodController addStateHoodController = new AddStateHoodController(getContext().getBean(StateHoodRepository.class));
            URL resource = getClass().getResource("/ru/akhitev/rp/state/addEditStateHood.fxml");
            FXMLLoader loader = new FXMLLoader(resource);
            loader.setController(addStateHoodController);
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
            stage.setTitle("Добавление государства");
            stage.showAndWait();
        });
    }
}
