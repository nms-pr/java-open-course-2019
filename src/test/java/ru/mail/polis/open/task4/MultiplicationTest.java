package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplicationTest {

    @Test
    void test() {
        assertEquals(
                15,
                new Multiplication(
                        new Const(3),
                        new Const(5)
                ).evaluate()
        );
        assertEquals(
                120,
                new Multiplication(
                        new Const(12),
                        new Const(10)
                ).evaluate()
        );
    }
}