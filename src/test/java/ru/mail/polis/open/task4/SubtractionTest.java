package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubtractionTest {

    @Test
    void test() {
        assertEquals(
                11,
                new Subtraction(
                        new Const(15),
                        new Const(4)
                ).evaluate()
        );
        assertEquals(
                15,
                new Subtraction(
                        new Const(50),
                        new Const(35)
                ).evaluate()
        );
        assertEquals(
                -5,
                new Subtraction(
                        new Const(10),
                        new Const(15)
                ).evaluate()
        );
    }
}