package ru.akhitev.rp.production.repo;

import org.springframework.data.repository.CrudRepository;
import ru.akhitev.rp.production.entity.CriticalProduction;

public interface CriticalProductionRepo extends CrudRepository<CriticalProduction, Long> {
}
