package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnMinTest {

    @Test
    void test() {
        assertEquals(
            -5,
            new UnMin(
                new Const(5)
            ).evaluate()
        );
    }

    @Test
    void testWorkingEquals() {
        UnMin unMin1 = new UnMin(
            new Const(
                9)
        );

        UnMin unMin2 = new UnMin(
            new Const(
                9)
        );

        assertEquals(unMin1, unMin2);
    }
}
