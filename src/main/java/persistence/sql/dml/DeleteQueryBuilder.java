package persistence.sql.dml;

import jakarta.persistence.Entity;
import persistence.sql.ddl.Table;
import persistence.sql.exception.InvalidEntityException;

public class DeleteQueryBuilder {
    public static final String DELETE_ALL_QUERY = "DELETE FROM %s";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM %s where %s = %d";
    private final Table table;

    public DeleteQueryBuilder(Class<?> entity) {
        if (!entity.isAnnotationPresent(Entity.class)) {
            throw new InvalidEntityException();
        }
        this.table = new Table(entity);
    }

    public String deleteAll() {
        return String.format(DELETE_ALL_QUERY, table.name());
    }
    public String deleteById(Long id) {
        return String.format(DELETE_BY_ID_QUERY, table.name(), table.primaryKeyName(), id);
    }
}
