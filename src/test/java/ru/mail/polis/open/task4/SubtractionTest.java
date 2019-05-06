package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtractionTest {

    @Test
    void test() {

        assertEquals(
                35,
                new Subtraction(
                        new Const(50),
                        new Const(15)
                ).evaluate()
        );

        assertEquals(
                -10,
                new Subtraction(
                        new Const(-7),
                        new Const(3)
                ).evaluate()
        );

    }

}
