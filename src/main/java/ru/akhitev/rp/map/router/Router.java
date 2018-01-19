package ru.akhitev.rp.map.router;

import ru.akhitev.rp.map.drawer.DrawingProperties;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class Router {
    private static final double LIGHT_YEARS_IN_ONE_PIXEL = 7.9d;
    private List<Point> points;

    @Inject
    private DrawingProperties drawingProperties;

    public void startRoute(double x, double y) {
        points = new ArrayList<>();
        points.add(new Point(x, y));
    }

    public Double finishRouteAndCalculate(double x, Double y) {
        finishRoute(x, y);
        return calculate();
    }

    public boolean routeDidNotStarted() {
        return points == null || points.isEmpty();
    }

    public String getPointsInfo() {
        String format = "Точка А [%.2f и %.2f]\r\nТочка Б [%.2f и %.2f]";
        return String.format(format, scale(points.get(0).x), scale(points.get(0).y), scale(points.get(1).x), scale(points.get(0).y));
    }

    public void reset() {
        points = null;
    }

    private Double calculate() {
        Point point1 = points.get(0);
        Point point2 = points.get(1);
        double differenceX = point1.x - point2.x;
        double differenceY = point1.y - point2.y;
        double distanceInPixel = Math.sqrt(differenceX * differenceX + differenceY * differenceY);
        double scaledDistanceInPixel = scale(distanceInPixel);
        double danceInLightYears = scaledDistanceInPixel / LIGHT_YEARS_IN_ONE_PIXEL;
        return danceInLightYears;
    }

    private void finishRoute(Double x, Double y) {
        points.add(new Point(x, y));
    }

    private double scale(double number) {
        return number / drawingProperties.getScale();
    }

    private class Point {
        private Double x;
        private Double y;

        private Point(Double x, Double y) {
            this.x = x;
            this.y = y;
        }
    }
}
