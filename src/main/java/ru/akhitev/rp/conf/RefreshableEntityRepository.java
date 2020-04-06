package ru.akhitev.rp.conf;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface RefreshableEntityRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
    void refresh(T t);
}
