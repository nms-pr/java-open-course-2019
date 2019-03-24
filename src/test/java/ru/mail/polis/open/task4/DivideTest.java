package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivideTest {

    @Test
    void test() {
        assertEquals(
                5,
                new Divide(
                        new Const(15),
                        new Const(3)
                ).evaluate()
        );
        assertEquals(2,
                new Divide(
                        new Add(new Const(10),
                                new Const(4)),
                        new Substract(new Const(5),
                                new Const(-2))).evaluate());
    }

}
