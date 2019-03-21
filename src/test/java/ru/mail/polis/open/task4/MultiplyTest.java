package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplyTest {

    @Test
    void evaluate() {

        assertEquals(15,
            new Multiply(
                    new Const(3),
                    new Const(5)
            ).evaluate()
        );
    }

    @Test
    void equals() {
        Multiply multiply = new Multiply(
            new Const(2),
            new Const(3)
        );

        assertEquals(multiply, multiply);

        assertEquals(
            new Multiply(
                new Const(2),
                new Const(3)
            ),

            new Multiply(
                new Const(2),
                new Const(3)
            )
        );
    }
}