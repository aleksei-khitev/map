package ru.akhitev.rp.fleet.repo;

import org.springframework.data.repository.CrudRepository;
import ru.akhitev.rp.fleet.entity.FleetUnit;

public interface FleetUnitRepo extends CrudRepository<FleetUnit, Long> {
}
