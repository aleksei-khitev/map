package ru.akhitev.rp.map.drawer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import ru.akhitev.rp.map.entity.StarSystem;
import ru.akhitev.rp.map.repository.StarSystemRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@Named
public class StarSystemDrawer implements Drawer {
    @Inject
    private StarSystemRepository starSystemRepository;

    @Inject
    private DrawingProperties drawingProperties;

    @Override
    public void draw(Canvas map) {
        starSystemRepository.findAll().forEach(starSystem -> {drawSystem(map, starSystem);});
    }

    private void drawSystem(Canvas map, StarSystem starSystem) {
        GraphicsContext graphicsContext2D = map.getGraphicsContext2D();
        graphicsContext2D.setFill(Color.valueOf(starSystem.getStatehood().getColor()));
        drawStarSystemIcon(graphicsContext2D, starSystem);
        drawStarSystemName(graphicsContext2D, starSystem);
    }

    private void drawStarSystemIcon(GraphicsContext graphicsContext2D, StarSystem starSystem) {
        try {
            graphicsContext2D.drawImage(new Image(new FileInputStream(starSystem.getStatehood().getImage())), scaleCoordinate(starSystem.getCoordinateX()),scaleCoordinate(starSystem.getCoordinateY()),15,15);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void drawStarSystemName(GraphicsContext graphicsContext2D, StarSystem starSystem) {
        graphicsContext2D.fillText(starSystem.getName(), scaleCoordinate(starSystem.getCoordinateX()) + 15, scaleCoordinate(starSystem.getCoordinateY()));
    }

    private Double scaleCoordinate(Double coordinate) {
        return coordinate * drawingProperties.getScale();
    }
}
