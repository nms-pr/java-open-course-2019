package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisionTest {
    @Test
    void test() {
        assertEquals(
                2,
                new Division(
                        new Const(22),
                        new Const(11)
                ).evaluate()
        );
        assertEquals(
                5,
                new Division(
                        new Add(new Const(3),
                                new Const(7)),
                        new Subtract(new Const(77),
                                new Const(75))
                ).evaluate()
        );
    }

}