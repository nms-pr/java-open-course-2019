package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplicationTest {
    @Test
    void test() {
        assertEquals(
                28,
                new Multiplication(
                        new Const(7),
                        new Const(4)
                ).evaluate()
        );
    }
}
