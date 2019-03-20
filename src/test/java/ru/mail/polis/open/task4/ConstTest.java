package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
class ConstTest {

    @Test
    void test() {
        assertEquals(
            5,
            new Const(
                5
            ).evaluate()
        );
    }

    @Test
    void testWorkingEquals() {
        Const const1 = new Const(4);
        Const const2 = new Const(4);
        assertEquals(const1, const2);
    }
}