package ru.akhitev.rp.map.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javax.inject.Inject;

import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import ru.akhitev.rp.map.drawer.DrawingProperties;
import ru.akhitev.rp.map.drawer.GridOfCoordinatesDrawer;
import ru.akhitev.rp.map.drawer.StarSystemDrawer;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class MainController implements Initializable {
    private static final Integer MAP_WIDTH = 1000;
    private static final Integer MAP_HEIGHT = 1000;
    @FXML
    private Canvas map;

    @FXML
    private TextArea objectInfo;

    @Inject
    private GridOfCoordinatesDrawer gridOfCoordinatesDrawer;

    @Inject
    private StarSystemDrawer starSystemDrawer;

    @Inject
    private DrawingProperties drawingProperties;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawingProperties.setScale(1);
        initialize();
    }

    @FXML
    public void setScaleX1() {
        drawingProperties.setScale(1);
        initialize();
    }

    @FXML
    public void setScaleX2() {
        drawingProperties.setScale(2);
        initialize();
    }

    @FXML
    public void setScaleX3() {
        drawingProperties.setScale(3);
        initialize();
    }

    private void initialize() {
        clearMap();
        map.setWidth(MAP_WIDTH * drawingProperties.getScale());
        map.setHeight(MAP_HEIGHT * drawingProperties.getScale());
        gridOfCoordinatesDrawer.draw(map);
        starSystemDrawer.draw(map);
    }

    private void clearMap() {
        GraphicsContext gc = map.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, map.getWidth(), map.getHeight());
        gc.clearRect(0, 0, map.getWidth(), map.getHeight());
    }
}
