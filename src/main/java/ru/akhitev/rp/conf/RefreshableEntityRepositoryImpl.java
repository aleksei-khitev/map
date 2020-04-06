package ru.akhitev.rp.conf;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

public class RefreshableEntityRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements RefreshableEntityRepository<T, ID> {
    private final EntityManager entityManager;

    public RefreshableEntityRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void refresh(T t) {
        this.entityManager.refresh(this.entityManager.merge(t));
    }
}
