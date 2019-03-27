package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MultTest {

    @Test
    void test() {
        assertEquals(
            2,
            new Mult(
                new Const(1),
                new Const(2)
            ).evaluate()
        );
        assertEquals(
            12,
            new Mult(
                new Mult(
                    new Const(2),
                    new Const(2)
                ),
                new Const(3)).evaluate()
        );
        assertEquals(
            6,
            new Mult(
                new Div(
                    new Const(4),
                    new Const(2)
                ),
                new Const(3)).evaluate()
        );
    }
    
}