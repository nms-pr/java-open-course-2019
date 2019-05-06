package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplicationTest {

    @Test
    void test() {

        assertEquals(
                72,
                new Multiplication(
                        new Const(8),
                        new Const(9)
                ).evaluate()
        );

        assertEquals(
                -14,
                new Multiplication(
                        new Const(-7),
                        new Const(2)
                ).evaluate()
        );

        assertEquals(
                30,
                new Multiplication(
                        new Const(-5),
                        new Const(-6)
                ).evaluate()
        );

    }

}
