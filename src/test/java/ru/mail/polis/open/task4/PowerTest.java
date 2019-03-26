package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowerTest {

    @Test
    void test() {
        assertEquals(
                64,
                new Power(
                        new Const(4),
                        new Const(3)
                ).evaluate()
        );
        assertEquals(
                121,
                new Power(
                        new Const(11),
                        new Const(2)
                ).evaluate()
        );
    }
}