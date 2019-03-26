package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddTest {

    @Test
    void test() {
        assertEquals(
                5,
                new Add(
                        new Const(2),
                        new Const(3)
                ).evaluate()
        );
        assertEquals(25,
                new Add(
                        new Multiply(new Const(3),
                                new Const(5)),
                        new Division(new Const(31),
                                new Const(3))
                ).evaluate());
    }


}