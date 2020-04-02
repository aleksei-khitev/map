package ru.akhitev.rp.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.rp.map.entity.StarSystem;
import ru.akhitev.rp.map.entity.StateHood;

import java.util.Set;

public interface StarSystemRepository extends JpaRepository<StarSystem, Integer>, StarSystemRepositoryCustom {
    Set<StarSystem> findByStatehood(StateHood stateHood);
}
