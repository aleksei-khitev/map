package ru.akhitev.rp.star_system.repo;

import org.springframework.data.repository.CrudRepository;
import ru.akhitev.rp.star_system.entity.PlanetResource;

public interface PlanetResourceRepo extends CrudRepository<PlanetResource, Long> {
}
