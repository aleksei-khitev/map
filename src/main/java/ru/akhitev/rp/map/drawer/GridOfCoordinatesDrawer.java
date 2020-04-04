package ru.akhitev.rp.map.drawer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GridOfCoordinatesDrawer implements Drawer{
    private static final Integer SIZE_OF_CELL = 100;
    private static final Integer SIZE_OF_SUB_CELL = 10;
    private static final Double CELL_LINE_WIDTH = 0.5;
    private static final Double SUB_CELL_LINE_WIDTH = 0.25;

    @Autowired
    private ScalingManager scalingManager;

    public void draw(Canvas map) {
        drawGrid(map);
    }

    private void drawGrid(Canvas map) {
        GraphicsContext graphicsContext = map.getGraphicsContext2D();
        graphicsContext.setFill(Color.GRAY);
        graphicsContext.setStroke(Color.LIGHTGRAY);
        graphicsContext.setLineWidth(CELL_LINE_WIDTH);
        drawX(map, graphicsContext, scalingManager.getScale());
        drawY(map, graphicsContext, scalingManager.getScale());
        graphicsContext.setFill(Color.GRAY);
        graphicsContext.setStroke(Color.LIGHTGRAY);
        graphicsContext.setLineWidth(SUB_CELL_LINE_WIDTH);
        drawSubX(map, graphicsContext, scalingManager.getScale());
        drawSubY(map, graphicsContext, scalingManager.getScale());
    }

    private void drawX(Canvas map, GraphicsContext graphicsContext, Integer scale) {
        for (int x = 0; x < map.getWidth(); x+=getCellSize(scale)) {
            graphicsContext.fillText(getCoordinateText(x, scale), x, 10);
            graphicsContext.strokeLine(x, 15, x, map.getHeight());
        }
    }

    private void drawSubX(Canvas map, GraphicsContext graphicsContext, Integer scale) {
        for (int x = 0; x < map.getWidth(); x+=getSubCellSize(scale)) {
            graphicsContext.strokeLine(x, 15, x, map.getHeight());
        }
    }

    private void drawY(Canvas map, GraphicsContext graphicsContext, Integer scale) {
        for (int y = 0; y < map.getHeight(); y+=getCellSize(scale)) {
            graphicsContext.fillText(getCoordinateText(y, scale), 10, y);
            graphicsContext.strokeLine(15, y, map.getWidth(), y);
        }
    }

    private void drawSubY(Canvas map, GraphicsContext graphicsContext, Integer scale) {
        for (int y = 0; y < map.getHeight(); y+=getSubCellSize(scale)) {
            graphicsContext.strokeLine(15, y, map.getWidth(), y);
        }
    }

    private Integer getCellSize(Integer scale) {
        return SIZE_OF_CELL * scale;
    }

    private Integer getSubCellSize(Integer scale) {
        return SIZE_OF_SUB_CELL * scale;
    }

    private String getCoordinateText(Integer number, Integer scale) {
        return String.valueOf(number / scale);
    }
}
