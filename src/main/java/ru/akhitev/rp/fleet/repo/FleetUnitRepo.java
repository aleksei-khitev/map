package ru.akhitev.rp.fleet.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.akhitev.rp.fleet.entity.FleetUnit;
import ru.akhitev.rp.fleet.entity.FleetUnitShort;

import java.util.Set;

public interface FleetUnitRepo extends CrudRepository<FleetUnit, Long> {
    @Query("select fu from FleetUnit fu")
    Set<FleetUnitShort> findAllForShortForm();
}
