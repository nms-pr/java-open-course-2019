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
    void equals() {

        Add add = new Add(
            new Const(2),
            new Const(3)
        );

        assertEquals(add, add);

        assertEquals(
            new Add(
                new Const(2),
                new Const(3)
            ),

            new Add(
                new Const(2),
                new Const(3)
            )
        );
    }

}