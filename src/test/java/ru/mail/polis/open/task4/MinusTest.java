package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MinusTest {

    @Test
    void test() {
        assertEquals(
            -5,
            new Minus(
                5
            ).evaluate()
        );
        assertEquals(
            -3,
            new Minus(
                3
            ).evaluate()
        );
        assertEquals(
            0,
            new Const(
                0
            ).evaluate()
        );
    }
}