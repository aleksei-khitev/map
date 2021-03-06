package ru.akhitev.rp.star_system.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.akhitev.rp.map.drawer.ScalingManager;
import ru.akhitev.rp.star_system.drawer.AbstractStarSystemDrawer;
import ru.akhitev.rp.star_system.entity.StarSystem;

public class CapitalOfStateHoodDrawer extends AbstractStarSystemDrawer {
    private static final Integer SIZE = 16;
    private static final Integer HORIZONTAL_INDENT = 8;

    private GraphicsContext graphicsContext2D;
    private StarSystem starSystem;

    public CapitalOfStateHoodDrawer(GraphicsContext graphicsContext2D, StarSystem starSystem, ScalingManager scalingManager) {
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
    public boolean isNameVisible() {
        return true;
    }

    @Override
    public GraphicsContext getGraphicsContext() {
        return graphicsContext2D;
    }

    @Override
    public StarSystem getStarSystem() {
        return starSystem;
    }

    @Override
    public Integer getHorizontalIndent() {
        return HORIZONTAL_INDENT;
    }
}
