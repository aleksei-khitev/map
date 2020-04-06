package ru.akhitev.rp.ship.repo;

import org.springframework.data.repository.CrudRepository;
import ru.akhitev.rp.ship.entity.Ship;

public interface ShipRepo extends CrudRepository<Ship, Long> {
}
