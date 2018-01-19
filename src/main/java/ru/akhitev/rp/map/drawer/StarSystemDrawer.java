package ru.akhitev.rp.map.drawer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
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
        drawStarSystemFigure(graphicsContext2D, starSystem);
        int horisontalIndent = 0;
        drawStarSystemName(graphicsContext2D, starSystem, horisontalIndent);
    }

    public void drawEmblems(Canvas map, StarSystem starSystem) {
        int horisontalIndent = 0;
        GraphicsContext graphicsContext2D = map.getGraphicsContext2D();
        if (starSystem.getSuperStatehood()!= null && starSystem.getSuperStatehood().getImage() != null) {
            drawIcon(graphicsContext2D, starSystem, starSystem.getSuperStatehood().getImage(), horisontalIndent);
            horisontalIndent = horisontalIndent + 80;
        }
        if (starSystem.getStatehood()!= null && starSystem.getStatehood().getImage() != null) {
            drawIcon(graphicsContext2D, starSystem, starSystem.getStatehood().getImage(), horisontalIndent);
        }
    }

    private void drawStarSystemFigure(GraphicsContext graphicsContext2D, StarSystem starSystem) {
        if (starSystem.getSuperStatehood() != null) {
            Stop[] stops1 = new Stop[]{new Stop(0, Color.valueOf(starSystem.getStatehood().getColor())),
                    new Stop(1, Color.valueOf(starSystem.getSuperStatehood().getColor()))};
            RadialGradient lg1 = new RadialGradient(0, 0, 0.5, 0.5, 0.8, true,
                    CycleMethod.NO_CYCLE, stops1);
            graphicsContext2D.setFill(lg1);
        } else {
            graphicsContext2D.setFill(Color.valueOf(starSystem.getStatehood().getColor()));
        }
        graphicsContext2D.fillOval(drawingProperties.scaleCoordinate(starSystem.getCoordinateX()), drawingProperties.scaleCoordinate(starSystem.getCoordinateY()), 15, 15);
        graphicsContext2D.setFill(Color.valueOf(starSystem.getStatehood().getColor()));
    }

    private void drawIcon(GraphicsContext graphicsContext2D, StarSystem starSystem, String image, int horisontalIndent) {
        try {
            graphicsContext2D.drawImage(new Image(new FileInputStream(image)), horisontalIndent, 0,80,80);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void drawStarSystemName(GraphicsContext graphicsContext2D, StarSystem starSystem, int horisontalIndent) {
        if (starSystem.getSuperStatehood() != null) {
            graphicsContext2D.setFill(Color.valueOf(starSystem.getSuperStatehood().getColor()));
        } else {
            graphicsContext2D.setFill(Color.valueOf(starSystem.getStatehood().getColor()));
        }
        graphicsContext2D.fillText(starSystem.getName(), drawingProperties.scaleCoordinate(starSystem.getCoordinateX()) + horisontalIndent, drawingProperties.scaleCoordinate(starSystem.getCoordinateY()));
    }

}
