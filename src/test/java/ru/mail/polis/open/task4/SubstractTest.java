package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubstractTest {

    @Test
    void test() {
        assertEquals(
                19,
                new Substract(
                        new Const(51),
                        new Const(32)
                ).evaluate()
        );
        assertEquals(-3,
                new Substract(
                        new Add(new Const(0),
                                new Const(6)),
                        new Power(new Const(3),
                                new Const(2))
                ).evaluate());
    }

}
