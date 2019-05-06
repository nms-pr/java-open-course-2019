package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerTest {

    @Test
    void test() {

        assertEquals(
                25,
                new Power(
                        new Const(5),
                        new Const(2)
                ).evaluate()
        );

        assertEquals(
                -7,
                new Power(
                        new Const(-7),
                        new Const(1)
                ).evaluate()
        );

    }

}
