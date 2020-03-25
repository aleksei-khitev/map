package ru.akhitev.rp.fleet.repo;

import org.springframework.data.repository.CrudRepository;
import ru.akhitev.rp.fleet.entity.Ship;

public interface ShipRepo extends CrudRepository<Ship, Long> {
}
