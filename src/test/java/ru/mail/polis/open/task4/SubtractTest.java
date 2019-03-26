package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubtractTest {

    @Test
    void test() {
        assertEquals(
                37,
                new Subtract(
                        new Const(137),
                        new Const(100)
                ).evaluate()
        );
        assertEquals(2,
                new Subtract(
                        new Power(new Const(2),
                                new Const(3)),
                        new Multiply(new Const(3),
                                new Const(2))
                ).evaluate());
    }
}