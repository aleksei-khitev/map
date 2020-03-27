package ru.akhitev.rp.map.drawer;

import org.springframework.stereotype.Service;

@Service
public class ScalingManager {
    private Integer scale;
    public Double scaleCoordinate(Double coordinate) {
        return coordinate * scale;
    }
    public Double reScaleCoordinate(Double coordinate) {
        return coordinate / scale;
    }
    public Integer getScale() {
        return scale;
    }
    public void setScale(Integer scale) {
        this.scale = scale;
    }
}
