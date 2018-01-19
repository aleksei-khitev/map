package ru.akhitev.rp.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.rp.map.entity.StarSystem;

public interface StarSystemRepository extends JpaRepository<StarSystem, Integer>, StarSystemRepositoryCustom {
}
