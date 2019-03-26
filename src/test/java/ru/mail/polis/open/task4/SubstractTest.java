package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubstractTest {
    @Test
    void test() {
        assertEquals(
                27,
                new Substract(
                        new Const(30),
                        new Const(3)
                ).evaluate()
        );
    }
}