package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtractionTest {
    @Test
    void test() {
        assertEquals(
                3,
                new Subtraction(
                        new Const(10),
                        new Const(7)
                ).evaluate()
        );
    }
}
