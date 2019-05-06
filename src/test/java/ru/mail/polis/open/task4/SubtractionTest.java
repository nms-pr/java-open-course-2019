package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubtractionTest {

    @Test
    void subTest() {
        assertEquals(
                2,
                new Subtraction(
                        new Const(3),
                        new Const(1)
                ).evaluate()
        );
    }
}