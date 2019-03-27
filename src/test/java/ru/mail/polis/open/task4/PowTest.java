package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PowTest {

    @Test
    void test() {
        assertEquals(
            1,
            new Pow(
                new Const(1),
                new Const(2)
            ).evaluate()
        );
        assertEquals(
            64,
            new Pow(
                new Mult(
                    new Const(2),
                    new Const(2)
                ),
                new Const(3)).evaluate()
        );
        assertEquals(
            8,
            new Pow(
                new Div(
                    new Const(4),
                    new Const(2)
                ),
                new Const(3)).evaluate()
        );
    }

}