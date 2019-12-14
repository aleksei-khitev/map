package ru.akhitev.rp.fleet.ship.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.rp.fleet.ship.db.entity.Ship;

public interface ShipRepository extends JpaRepository<Ship, Integer> {
}
