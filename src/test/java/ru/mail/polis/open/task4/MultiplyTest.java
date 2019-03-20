package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyTest {

    @Test
    void evaluate() {

        assertEquals(15,
            new Multiply(
                    new Const(3),
                    new Const(5)
            ).evaluate()
        );
    }
}