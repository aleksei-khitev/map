package ru.akhitev.rp.fleet.fleet_node.ui;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akhitev.rp.conf.AbstractController;
import ru.akhitev.rp.fleet.ship.db.repo.ShipRepository;

import java.util.stream.Collectors;

@Component
public class FleetController extends AbstractController {
    public TextArea ships;
    public Button show;
    @Autowired
    private ShipRepository shipRepository;

    @FXML
    public void initialize() {
        show.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            shipRepository.findAll().stream().map(ship -> ship.getName()).collect(Collectors.toList()).forEach(ship -> ships.appendText(ship + "\n"));
        });
    }
}
