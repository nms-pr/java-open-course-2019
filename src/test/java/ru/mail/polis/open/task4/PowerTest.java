package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowerTest {
    @Test
    void test() {
        assertEquals(
                1,
                new Power(
                        new Const(9991),
                        new Const(0)
                ).evaluate()
        );
    }

}