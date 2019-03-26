package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplyTest {
    @Test
    void test() {
        assertEquals(
                18,
                new Multiply(
                        new Const(6),
                        new Const(3)
                ).evaluate()
        );
    }
}