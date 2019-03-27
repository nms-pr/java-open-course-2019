package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubTest {

    @Test
    void test() {
        assertEquals(
            -1,
            new Sub(
                new Const(1),
                new Const(2)
            ).evaluate()
        );
        assertEquals(
            1,
            new Sub(
                new Mult(
                    new Const(2),
                    new Const(2)
                ),
                new Const(3)).evaluate()
        );
        assertEquals(
            -1,
            new Sub(
                new Div(
                    new Const(4),
                    new Const(2)
                ),
                new Const(3)).evaluate()
        );
    }
}