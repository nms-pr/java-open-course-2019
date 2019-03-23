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

    @Test
    void testWorkingEquals() {
        Pow pow1 = new Pow(
            new Const(9),
            new Const(3)
        );

        Pow pow2 = new Pow(
            new Const(9),
            new Const(3)
        );

        assertEquals(pow1, pow2);
    }
}
