package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DivisionTest {

    @Test
    void test() {
        assertEquals(
                3,
                new Division(
                        new Const(6),
                        new Const(2)
                ).evaluate()
        );
        assertEquals(
                33,
                new Division(
                        new Const(100),
                        new Const(3)
                ).evaluate()
        );
    }

}