package persistence.entity;

import jdbc.JdbcTemplate;
import persistence.sql.common.DtoMapper;
import persistence.sql.ddl.PrimaryKeyClause;
import persistence.sql.dml.DeleteQueryBuilder;
import persistence.sql.dml.InsertQueryBuilder;
import persistence.sql.dml.SelectQueryBuilder;
import persistence.sql.dml.UpdateQueryBuilder;

public class EntityLoader<T> {
    private final JdbcTemplate jdbcTemplate;

    public EntityLoader(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public T find(Class<T> clazz, Long id) {
        String query = new SelectQueryBuilder(clazz).getFindById(id);
        return jdbcTemplate.queryForObject(query, new DtoMapper<>(clazz));
    }
}
