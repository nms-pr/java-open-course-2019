package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplyTest {
    @Test
    void test() {
        assertEquals(
                38,
                new Multiply(
                        new Const(19),
                        new Const(2)
                ).evaluate()
        );
    }

}