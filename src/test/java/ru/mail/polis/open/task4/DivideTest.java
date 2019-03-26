package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivideTest {
    @Test
    void test() {
        assertEquals(
                5,
                new Divide(
                        new Const(10),
                        new Const(2)
                ).evaluate()
        );
    }
}