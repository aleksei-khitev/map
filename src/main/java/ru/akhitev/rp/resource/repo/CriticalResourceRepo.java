package ru.akhitev.rp.resource.repo;

import org.springframework.data.repository.CrudRepository;
import ru.akhitev.rp.resource.entity.CriticalResource;

public interface CriticalResourceRepo extends CrudRepository<CriticalResource, Long> {
}
