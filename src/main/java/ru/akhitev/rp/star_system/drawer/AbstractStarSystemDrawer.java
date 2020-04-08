package ru.akhitev.rp.star_system.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.akhitev.rp.map.drawer.ScalingManager;
import ru.akhitev.rp.resource.entity.CriticalResource;
import ru.akhitev.rp.star_system.entity.PlanetResource;
import ru.akhitev.rp.star_system.entity.StarSystem;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractStarSystemDrawer {
    Logger logger = LoggerFactory.getLogger(AbstractStarSystemDrawer.class);
    protected ScalingManager scalingManager;

    public abstract void drawStarSystemFigure();

    void drawStarSystemName() {
        if (isNameVisible()) {
            prepareNameTextColor();
            getGraphicsContext().fillText(getStarSystem().getName(), scalingManager.scaleCoordinate(getStarSystem().getCoordinateX()) + getHorizontalIndent(), scalingManager.scaleCoordinate(getStarSystem().getCoordinateY()));
        }
    }

    void drawResources() {
        AtomicInteger horizontalIntend = new AtomicInteger(0);
        getStarSystem().getPlanets().stream()
                .filter(p -> p.getPlanetResources() != null && p.getPlanetResources().size() > 0)
                .flatMap(p -> p.getPlanetResources().stream())
                .map(PlanetResource::getCriticalResource)
                .distinct()
                .sorted(Comparator.comparing(CriticalResource::getId))
                .forEach(r -> {
                    Image image = new Image(AbstractStarSystemDrawer.class.getResourceAsStream("/" + r.getColor()),
                            15, 15, false, false);
                    getGraphicsContext().drawImage(image,
                            scalingManager.scaleCoordinate(getStarSystem().getCoordinateX()) + getHorizontalIndent() + horizontalIntend.getAndAdd(15),
                            scalingManager.scaleCoordinate(getStarSystem().getCoordinateY()));
                    logger.info(r.toString());
                });
    }

    void prepareFigureColors() {
        if (getStarSystem().getSuperStatehood() != null) {
            Stop[] stops1 = new Stop[]{new Stop(0, Color.valueOf(getStarSystem().getStatehood().getColor())),
                    new Stop(1, Color.valueOf(getStarSystem().getSuperStatehood().getColor()))};
            RadialGradient lg1 = new RadialGradient(0, 0, 0.5, 0.5, 0.8, true,
                    CycleMethod.NO_CYCLE, stops1);
            getGraphicsContext().setFill(lg1);
        } else {
            getGraphicsContext().setFill(Color.valueOf(getStarSystem().getStatehood().getColor()));
        }
    }

    private void prepareNameTextColor() {
        if (getStarSystem().getSuperStatehood() != null) {
            getGraphicsContext().setFill(Color.valueOf(getStarSystem().getSuperStatehood().getColor()));
        } else {
            getGraphicsContext().setFill(Color.valueOf(getStarSystem().getStatehood().getColor()));
        }
    }

    protected abstract boolean isNameVisible();

    protected abstract GraphicsContext getGraphicsContext();

    protected abstract StarSystem getStarSystem();

    protected abstract Integer getHorizontalIndent();
}
