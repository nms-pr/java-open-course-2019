package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
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
    }

    @Test
    void testWorkingEquals() {
        Add add1 = new Add(
            new Const(4),
            new Const(13)
        );

        Add add2 = new Add(
            new Const(4),
            new Const(13)
        );

        assertEquals(add1, add2);
    }
}