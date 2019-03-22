package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubTest {

    @Test
    void test() {
        assertEquals(
            5,
            new Sub(
                new Const(9),
                new Const(4)
            ).evaluate()
        );
    }

    @Test
    void testWorkingEquals() {
        Sub sub1 = new Sub(
            new Const(9),
            new Const(4)
        );

        Sub sub2 = new Sub(
            new Const(11),
            new Const(6)
        );

        assertEquals(sub1, sub2);
    }
}
