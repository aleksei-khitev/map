package ru.akhitev.rp.map.drawer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class GridOfCoordinatesDrawer implements Drawer{
    private static final Integer SIZE_OF_CELL = 100;
    private static final Double LINE_WIDTH = 0.5;

    @Inject
    private ScalingManager scalingManager;

    public void draw(Canvas map) {
        drawGrid(map);
    }

    private void drawGrid(Canvas map) {
        GraphicsContext graphicsContext = map.getGraphicsContext2D();
        graphicsContext.setFill(Color.GRAY);
        graphicsContext.setStroke(Color.LIGHTGRAY);
        graphicsContext.setLineWidth(LINE_WIDTH);
        drawX(map, graphicsContext, scalingManager.getScale());
        drawY(map, graphicsContext, scalingManager.getScale());
    }

    private void drawX(Canvas map, GraphicsContext graphicsContext, Integer scale) {
        for (int x = 0; x < map.getWidth(); x+=getCellSize(scale)) {
            graphicsContext.fillText(getCoordinateText(x, scale), x, 10);
            graphicsContext.strokeLine(x, 15, x, map.getHeight());
        }
    }

    private void drawY(Canvas map, GraphicsContext graphicsContext, Integer scale) {
        for (int y = 0; y < map.getHeight(); y+=getCellSize(scale)) {
            graphicsContext.fillText(getCoordinateText(y, scale), 10, y);
            graphicsContext.strokeLine(15, y, map.getWidth(), y);
        }
    }

    private Integer getCellSize(Integer scale) {
        return SIZE_OF_CELL * scale;
    }

    private String getCoordinateText(Integer number, Integer scale) {
        return String.valueOf(number / scale);
    }
}
