package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivideTest {

    @Test
    void evaluate() {
        assertEquals(
            3,
                new Divide(
                        new Const(15),
                        new Const(5)
                ).evaluate()
        );

        assertEquals(3,
                new Divide(
                        new Const(18),
                        new Const(5)
                ).evaluate()
        );
    }

    @Test
    void equals() {
        Divide divide = new Divide(
            new Const(2),
            new Const(3)
        );

        assertEquals(divide, divide);

        assertEquals(
            new Divide(
                new Const(2),
                new Const(3)
            ),

            new Divide(
                new Const(2),
                new Const(3)
            )
        );
    }
}