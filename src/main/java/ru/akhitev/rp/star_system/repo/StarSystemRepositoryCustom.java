package ru.akhitev.rp.star_system.repo;

import ru.akhitev.rp.star_system.entity.StarSystem;

import java.util.List;

public interface StarSystemRepositoryCustom {
    List<StarSystem> findNearCoordinates(double x, double y);
}
