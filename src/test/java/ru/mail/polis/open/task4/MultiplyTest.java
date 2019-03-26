package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplyTest {
    @Test
    void test() {
        assertEquals(
                10,
                new Multiply(
                        new Const(5),
                        new Const(2)
                ).evaluate()
        );
    }
}