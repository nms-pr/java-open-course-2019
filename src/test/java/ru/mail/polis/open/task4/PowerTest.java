package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowerTest {

    @Test
    void evaluate() {
        assertEquals(
            8,
                new Power(
                        new Const(2),
                        new Const(3)
                ).evaluate()
        );
    }

    @Test
    void equals() {
        Power power = new Power(
            new Const(2),
            new Const(3)
        );

        assertEquals(power, power);

        assertEquals(
            new Power(
                new Const(2),
                new Const(3)
            ),

            new Power(
                new Const(2),
                new Const(3)
            )
        );
    }
}