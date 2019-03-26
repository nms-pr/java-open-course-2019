package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DivisionTest {
    @Test
    void test() {
        assertEquals(
                7,
                new Division(
                        new Const(28),
                        new Const(4)
                ).evaluate()
        );
        assertEquals(
                -7,
                new Division(
                        new Const(-28),
                        new Const(4)
                ).evaluate()
        );
    }
}
