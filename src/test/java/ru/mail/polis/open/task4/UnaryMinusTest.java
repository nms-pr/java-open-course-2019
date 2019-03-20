package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnaryMinusTest {

    @Test
    void evaluate() {
        assertEquals(-5,
                new UnaryMinus(
                        new Const(5)
                ).evaluate()

        );

    }
}