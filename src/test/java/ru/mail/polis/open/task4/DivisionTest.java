package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DivisionTest {

    @Test
    void test() {

        assertEquals(
                5,
                new Division(
                        new Const(15),
                        new Const(3)
                ).evaluate()
        );

        assertEquals(
                -6,
                new Division(
                        new Const(-30),
                        new Const(5)
                ).evaluate()
        );

    }

}
