package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultTest {

    @Test
    void test() {
        assertEquals(
            24,
            new Mult(
                new Const(4),
                new Const(6)
            ).evaluate()
        );
    }

    @Test
    void testWorkingEquals() {
        Mult mult1 = new Mult(
            new Const(4),
            new Const(6)
        );

        Mult mult2 = new Mult(
            new Const(4),
            new Const(6)
        );

        assertEquals(mult1, mult2);
    }
}
