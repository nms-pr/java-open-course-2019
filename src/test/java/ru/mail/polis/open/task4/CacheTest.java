package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CacheTest {

    @Test
    void test() {
        Cache cache = new Cache(3);
        cache.put("3+7", new Builder().build("3+7"));
        cache.put("47/2 - (15+1 *2)", new Builder().build("47/2 - (15+1 *2)"));
        cache.put("24", new Builder().build("24"));
        assertEquals(6, cache.getExpression("47/2 - (15+1 *2)").evaluate());
        cache.put("9-8", new Builder().build("9-8"));
        assertThrows(NullPointerException.class, () -> cache.getExpression("3+7"));
    }
}
