package ru.akhitev.rp.star_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.rp.conf.RefreshableEntityRepository;
import ru.akhitev.rp.star_system.entity.StarSystem;
import ru.akhitev.rp.state_hood.entity.StateHood;

import java.util.Set;

public interface StarSystemRepository extends RefreshableEntityRepository<StarSystem, Integer>, StarSystemRepositoryCustom {
    Set<StarSystem> findByStatehood(StateHood stateHood);
}
