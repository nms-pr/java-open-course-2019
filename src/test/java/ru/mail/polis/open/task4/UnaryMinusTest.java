package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnaryMinusTest {
    @Test
    void test() {
        assertEquals(
                3,
                new UnaryMinus(
                        new Const(-3)
                ).evaluate()
        );
    }
}
