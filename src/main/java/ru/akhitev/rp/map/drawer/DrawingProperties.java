package ru.akhitev.rp.map.drawer;

import javax.inject.Named;

@Named
public class DrawingProperties {
    private Integer scale;

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }
}
