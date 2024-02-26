package persistence.sql.ddl.column;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Function;

public class Column {
    private static final Map<Type, Function<String, String>> typeToSqlMap = Map.of(
            String.class, fieldName -> String.format("%s VARCHAR(30)", fieldName),
            Integer.class, fieldName -> String.format("%s INT", fieldName),
            int.class, fieldName -> String.format("%s INT", fieldName)
    );
    private final String name;
    private final Class<?> type;
    private final NullClause nullClause;

    public Column(Field field) {
        this.name = getName(field);
        this.type = field.getType();
        this.nullClause = new NullClause(field);
    }

    public String getQuery() {
        return typeToSqlMap.get(type).apply(name) + " " + nullClause.getQuery();
    }

    private String getName(Field field) {
        jakarta.persistence.Column column = field.getAnnotation(jakarta.persistence.Column.class);
        if (column == null) {
            return field.getName();
        }
        if (column.name().isEmpty()) {
            return field.getName();
        }
        return column.name();
    }
}
