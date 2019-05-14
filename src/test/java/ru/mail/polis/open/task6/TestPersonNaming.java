package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestPersonNaming {
    @Test
    void testPersonNaming() {
        Manager paulManager = new Manager("Paul");
        assertEquals("Paul", paulManager.getName());
        assertThrows(IllegalArgumentException.class, () -> new Manager("Jack"));
    }
}
