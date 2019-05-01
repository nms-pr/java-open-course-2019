package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.paulManager;

public class TestPersonNaming {
    @Test
    void testPersonNaming() {
        assertEquals("Paul", paulManager.getName());
        assertThrows(IllegalArgumentException.class, () -> new Manager("Jack"));
    }
}
