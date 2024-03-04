package persistence.entity;

import database.DatabaseServer;
import database.H2;
import jdbc.JdbcTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.entity.notcolumn.Person;
import persistence.sql.ddl.CreateQueryBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static persistence.entity.TestFixture.person_영희_id있음;
import static persistence.entity.TestFixture.person_철수_id있음;

class PersistenceContextImplTest {
    PersistenceContext persistenceContext;

    @DisplayName("캐시에 저장된 데이터를 조회할 수 있다.")
    @Test
    void getEntityTest() {
        // given
        Map<Long, Object> initialCache = new HashMap<>();
        initialCache.put(1L, person_철수_id있음);
        persistenceContext = new PersistenceContextImpl(initialCache);

        // when
        Object actual = persistenceContext.getEntity(1L);

        // then
        Assertions.assertEquals(person_철수_id있음, actual);
    }

    @DisplayName("캐시에 데이터를 저장할 수 있다.")
    @Test
    void addEntityTest() {
        // given
        Map<Long, Object> initialCache = new HashMap<>();
        persistenceContext = new PersistenceContextImpl(initialCache);

        // when
        persistenceContext.addEntity(1L, person_철수_id있음);

        // then
        Assertions.assertEquals(person_철수_id있음, initialCache.get(1L));
        assertNull(initialCache.get(2L));
    }

    @DisplayName("캐시에 저장된 데이터를 지울 수 있다")
    @Test
    void removeEntityTest() {
        // given
        Map<Long, Object> initialCache = new HashMap<>();
        initialCache.put(1L, person_철수_id있음);
        initialCache.put(2L, person_영희_id있음);
        persistenceContext = new PersistenceContextImpl(initialCache);

        // when
        persistenceContext.removeEntity(person_철수_id있음);

        // then
        assertNull(initialCache.get(1L));
        Assertions.assertEquals(person_영희_id있음, initialCache.get(2L));
    }
}