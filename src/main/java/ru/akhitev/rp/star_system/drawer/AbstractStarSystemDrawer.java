package ru.akhitev.rp.star_system.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import ru.akhitev.rp.map.drawer.ScalingManager;
import ru.akhitev.rp.star_system.entity.StarSystem;

public abstract class AbstractStarSystemDrawer {
    protected ScalingManager scalingManager;

    public abstract void drawStarSystemFigure();

    public void drawStarSystemName() {
        if (isNameVisible()) {
            prepareNameTextColor();
            getGraphicsContext().fillText(getStarSystem().getName(), scalingManager.scaleCoordinate(getStarSystem().getCoordinateX()) + getHorizontalIndent(), scalingManager.scaleCoordinate(getStarSystem().getCoordinateY()));
        }
    }

    protected void prepareFigureColors() {
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
