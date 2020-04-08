package ru.akhitev.rp.star_system.drawer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import ru.akhitev.rp.map.drawer.ScalingManager;
import ru.akhitev.rp.resource.entity.CriticalResource;
import ru.akhitev.rp.star_system.entity.PlanetResource;
import ru.akhitev.rp.star_system.entity.StarSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ResourceSystemDrawer extends AbstractStarSystemDrawer {
    private static final Integer HORIZONTAL_INDENT = 5;

    private GraphicsContext graphicsContext2D;
    private StarSystem starSystem;
    private boolean isNameVisible;

    public ResourceSystemDrawer(GraphicsContext graphicsContext2D, StarSystem starSystem, ScalingManager scalingManager) {
        this.graphicsContext2D = graphicsContext2D;
        this.starSystem = starSystem;
        this.scalingManager = scalingManager;
    }

    @Override
    public void drawStarSystemFigure() {
        List<CriticalResource> criticalResources = getStarSystem().getPlanets().stream()
                .filter(p -> p.getPlanetResources() != null && p.getPlanetResources().size() > 0)
                .flatMap(p -> p.getPlanetResources().stream())
                .map(PlanetResource::getCriticalResource)
                .distinct()
                .collect(Collectors.toList());
        if (criticalResources.size() > 0) {
            isNameVisible = true;
            if (criticalResources.size() == 1) {
                //fillStateHoodOval(13, 2);
                graphicsContext2D.setFill(criticalResources.get(0).getColor());
                graphicsContext2D.fillArc(scalingManager.scaleCoordinate(getStarSystem().getCoordinateX()) + getHorizontalIndent(),
                        scalingManager.scaleCoordinate(getStarSystem().getCoordinateY()), 13, 13, 0, 360, ArcType.ROUND);
            }else if (criticalResources.size() == 2) {
                //fillStateHoodOval(16, 2);
                graphicsContext2D.setFill(criticalResources.get(0).getColor());
                graphicsContext2D.fillArc(scalingManager.scaleCoordinate(getStarSystem().getCoordinateX()) + getHorizontalIndent(),
                        scalingManager.scaleCoordinate(getStarSystem().getCoordinateY()), 16, 16, 0, 180, ArcType.ROUND);
                graphicsContext2D.setFill(criticalResources.get(1).getColor());
                graphicsContext2D.fillArc(scalingManager.scaleCoordinate(getStarSystem().getCoordinateX()) + getHorizontalIndent(),
                        scalingManager.scaleCoordinate(getStarSystem().getCoordinateY()), 16, 16, 180, 180, ArcType.ROUND);
            } else if (criticalResources.size() == 3) {
                //fillStateHoodOval(20, 2);
                graphicsContext2D.setFill(criticalResources.get(0).getColor());
                graphicsContext2D.fillArc(scalingManager.scaleCoordinate(getStarSystem().getCoordinateX()) + getHorizontalIndent(),
                        scalingManager.scaleCoordinate(getStarSystem().getCoordinateY()), 20, 20, 0, 120, ArcType.ROUND);
                graphicsContext2D.setFill(criticalResources.get(1).getColor());
                graphicsContext2D.fillArc(scalingManager.scaleCoordinate(getStarSystem().getCoordinateX()) + getHorizontalIndent(),
                        scalingManager.scaleCoordinate(getStarSystem().getCoordinateY()), 20, 20, 120, 120, ArcType.ROUND);
                graphicsContext2D.setFill(criticalResources.get(2).getColor());
                graphicsContext2D.fillArc(scalingManager.scaleCoordinate(getStarSystem().getCoordinateX()) + getHorizontalIndent(),
                        scalingManager.scaleCoordinate(getStarSystem().getCoordinateY()), 20, 20, 240, 120, ArcType.ROUND);
            }
        } else {
            isNameVisible = false;
            graphicsContext2D.setFill(Color.WHITE);
            graphicsContext2D.fillArc(scalingManager.scaleCoordinate(getStarSystem().getCoordinateX()) + getHorizontalIndent(),
                    scalingManager.scaleCoordinate(getStarSystem().getCoordinateY()), 10, 10, 0, 360, ArcType.ROUND);
        }
    }

    private void fillStateHoodOval(int size, int increase) {
        prepareFigureColors();
        double x = scalingManager.scaleCoordinate(getStarSystem().getCoordinateX()) + getHorizontalIndent() - increase;
        Double y = scalingManager.scaleCoordinate(getStarSystem().getCoordinateY()) - increase;
        size = size + increase * 2;
        graphicsContext2D.fillOval(x,
                y,
                size, size);
    }

    @Override
    protected boolean isNameVisible() {
        return  isNameVisible;
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
