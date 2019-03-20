package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerTest {

    @Test
    void evaluate() {
        assertEquals(8,
                new Power(
                        new Const(2),
                        new Const(3)
                ).evaluate()
        );
    }
}