package ru.akhitev.rp.star_system.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.akhitev.rp.map.drawer.ScalingManager;
import ru.akhitev.rp.star_system.drawer.AbstractStarSystemDrawer;
import ru.akhitev.rp.star_system.entity.StarSystem;

public class ImportantSystemDrawer extends AbstractStarSystemDrawer {
    private static final Integer SIZE = 13;
    private static final Integer HORIZONTAL_INDENT = 7;

    private GraphicsContext graphicsContext2D;
    private StarSystem starSystem;

    ImportantSystemDrawer(GraphicsContext graphicsContext2D, StarSystem starSystem, ScalingManager scalingManager) {
        this.graphicsContext2D = graphicsContext2D;
        this.starSystem = starSystem;
        this.scalingManager = scalingManager;
    }

    @Override
    public void drawStarSystemFigure() {
        prepareFigureColors();
        graphicsContext2D.fillOval(scalingManager.scaleCoordinate(starSystem.getCoordinateX()), scalingManager.scaleCoordinate(starSystem.getCoordinateY()), SIZE, SIZE);
        graphicsContext2D.setFill(Color.valueOf(starSystem.getStatehood().getColor()));
    }

    @Override
    protected boolean isNameVisible() {
        return true;
    }

    @Override
    protected GraphicsContext getGraphicsContext() {
        return graphicsContext2D;
    }

    @Override
    protected StarSystem getStarSystem() {
        return starSystem;
    }

    @Override
    protected Integer getHorizontalIndent() {
        return HORIZONTAL_INDENT;
    }
}
