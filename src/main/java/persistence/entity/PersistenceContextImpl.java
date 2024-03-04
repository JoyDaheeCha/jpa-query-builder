package persistence.entity;

import jakarta.persistence.Id;
import persistence.entity.exception.InvalidPrimaryKeyException;
import persistence.sql.ddl.PrimaryKeyClause;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PersistenceContextImpl implements PersistenceContext{
    private final Map <Long,Object> entityCache;

    public PersistenceContextImpl() {
        this.entityCache = new HashMap<>();
    }

    public PersistenceContextImpl(Map <Long,Object> entityCache) {
        this.entityCache = entityCache;
    }

    @Override
    public Object getEntity(Long id) {
        return entityCache.get(id);
    }

    @Override
    public void addEntity(Long id, Object entity) {
        entityCache.put(id, entity);
    }

    @Override
    public void removeEntity(Object entity) {
        Long id = PrimaryKeyClause.primaryKeyValue(entity);
        entityCache.remove(id);
    }
}
