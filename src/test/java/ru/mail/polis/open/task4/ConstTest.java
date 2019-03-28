package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstTest {

    @Test
    void test() {
        assertEquals(5, new Const(5).evaluate());
    }

    @Test
    void testWorkingEquals() {
        Const c1 = new Const(20);
        Const c2 = new Const(20);
        assertEquals(c1, c2);
    }

    @Test
    void testEvaluate() {
        assertEquals(100, new Const(100).evaluate());
    }

}