package ru.akhitev.rp.map.repository;

import ru.akhitev.rp.map.entity.StarSystem;

import java.util.List;

public interface StarSystemRepositoryCustom {
    List<StarSystem> findNearCoordinates(double x, double y);
}
