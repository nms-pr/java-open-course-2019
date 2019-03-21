package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnaryMinusTest {

    @Test
    void evaluate() {
        assertEquals(
            -5,
                new UnaryMinus(
                        new Const(5)
                ).evaluate()

        );

    }

    @Test
    void equals() {
        UnaryMinus unaryMinus = new UnaryMinus(
            new Const(2)
        );

        assertEquals(unaryMinus, unaryMinus);

        assertEquals(
            new UnaryMinus(
                new Const(2)
            ),

            new UnaryMinus(
                new Const(2)
            )
        );
    }
}