package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowTest {

    @Test
    void test() {
        assertEquals(
            243,
            new Pow(
                new Const(3),
                new Const(5)
            ).evaluate()
        );
    }
}
