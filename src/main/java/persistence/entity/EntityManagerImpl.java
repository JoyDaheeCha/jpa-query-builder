package persistence.entity;

import jdbc.JdbcTemplate;
import persistence.sql.common.DtoMapper;
import persistence.sql.ddl.PrimaryKeyClause;
import persistence.sql.dml.DeleteQueryBuilder;
import persistence.sql.dml.InsertQueryBuilder;
import persistence.sql.dml.SelectQueryBuilder;

public class EntityManagerImpl<T> implements EntityManager<T>{
    private final EntityPersister entityPersister;
    private final EntityLoader<T> entityLoader;

    public EntityManagerImpl(JdbcTemplate jdbcTemplate) {
        this.entityPersister = new EntityPersister(jdbcTemplate);
        this.entityLoader = new EntityLoader<>(jdbcTemplate);
    }

    @Override
    public T find(Class<T> clazz, Long id) {
        return entityLoader.find(clazz, id);
    }

    @Override
    public Object persist(Object entity) {
        return entityPersister.insert(entity);
    }

    @Override
    public boolean update(Object entity) {
        return entityPersister.update(entity);
    }

    @Override
    public void remove(Object entity) {
        entityPersister.delete(entity);
    }
}
