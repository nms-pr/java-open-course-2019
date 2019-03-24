package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddTest {

    @Test
    void test() {
        assertEquals(
                3,
                new Add(
                        new Const(1),
                        new Const(2)
                ).evaluate()
        );
        assertEquals(13,
                new Add(
                        new Multiply(new Const(2),
                                new Const(5)),
                        new Divide(new Const(16),
                                new Const(5))
                ).evaluate());
    }

}