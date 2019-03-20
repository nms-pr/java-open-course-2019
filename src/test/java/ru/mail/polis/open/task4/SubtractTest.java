package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtractTest {

    @Test
    void evaluate() {
        assertEquals(5,
                new Subtract(
                        new Const(8),
                        new Const(3)
                ).evaluate()
        );
    }

    @Test
    void equals() {
        Subtract subtract = new Subtract(
            new Const(2),
            new Const(3)
        );

        assertEquals(subtract, subtract);

        assertEquals(
            new Subtract(
                new Const(2),
                new Const(3)
            ),

            new Subtract(
                new Const(2),
                new Const(3)
            )
        );
    }

}