package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowerTest {
    @Test
    void test() {
        assertEquals(
                81,
                new Power(
                        new Const(9),
                        new Const(2)
                ).evaluate()
        );
    }
}