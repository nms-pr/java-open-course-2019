package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplicationTest {

    @Test
    void multiplicationTest() {
        assertEquals(
                4,
                new Multiplication(
                        new Const(2),
                        new Const(2)
                ).evaluate()
        );
    }
}