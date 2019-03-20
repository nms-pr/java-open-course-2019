package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivideTest {

    @Test
    void evaluate() {
        assertEquals(3,
                new Divide(
                        new Const(15),
                        new Const(5)
                ).evaluate()
        );

        assertEquals(3,
                new Divide(
                        new Const(18),
                        new Const(5)
                ).evaluate()
        );
    }
}