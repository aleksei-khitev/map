package ru.akhitev.rp.fleet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhitev.rp.fleet.entity.Ship;
import ru.akhitev.rp.fleet.repo.ShipRepo;
import ru.akhitev.rp.fleet.vo.ShipShort;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ShipService {

    @Autowired
    private ShipRepo shipRepo;

    public Set<ShipShort> findAllShipsInShort() {
        Set<ShipShort> ships = new HashSet<>();
        shipRepo.findAll().forEach( ship -> ships.add(new ShipShort(ship.getId(), ship.getType(), ship.getShipClass())));
        return ships;
    }

    public Optional<Ship> getShipById(ShipShort shipShort) {
        if (shipShort == null) return Optional.empty();
        return shipRepo.findById(shipShort.getId());
    }
}
