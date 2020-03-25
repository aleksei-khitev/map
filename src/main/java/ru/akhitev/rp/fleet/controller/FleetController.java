package ru.akhitev.rp.fleet.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akhitev.rp.conf.AbstractController;
import ru.akhitev.rp.fleet.service.FleetService;

@Component
public class FleetController extends AbstractController {
    public TextArea ships;
    public Button show;

    @Autowired
    private FleetService fleetService;

    @FXML
    public void initialize() {
        show.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String result = fleetService.prepareReportForFleetUnitById(22L);
            ships.appendText(result);
        });
    }
}
