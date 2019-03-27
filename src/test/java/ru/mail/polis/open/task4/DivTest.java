package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DivTest {

    @Test
    void test() {
        assertEquals(
            2,
            new Div(
                new Const(5),
                new Const(2)
            ).evaluate()
        );
        assertEquals(
            1,
            new Div(
                new Mult(
                    new Const(2),
                    new Const(2)
                ),
                new Const(3)).evaluate()
        );
        assertEquals(
            2,
            new Div(
                new Div(
                    new Const(4),
                    new Const(2)
                ),
                new Const(1)).evaluate()
        );
    }
}