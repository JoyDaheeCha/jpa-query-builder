package persistence.sql.ddl.value;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.lang.reflect.Field;
import java.util.List;

public class ValueClauses {
    private final List<ValueClause> values;
    public ValueClauses(List<Field> fields, Object instance) {
        this.values = fields.stream()
                .filter(ValueClauses::isColumn)
                .map(field -> new ValueClause(field, instance)).toList();
    }

    private static boolean isColumn(Field field) {
        return !field.isAnnotationPresent(Transient.class) && !field.isAnnotationPresent(Id.class);
    }

    public List<String> getQueries() {
        return this.values.stream().map(ValueClause::value).toList();
    }

    public List<String> values() {
        return this.values.stream().map(ValueClause::value).toList();
    }
}
