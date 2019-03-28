package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubTest {

    @Test
    void testEvaluate() {
        assertEquals(44, new Sub(new Const(55), new Const(11)).evaluate());
    }

    @Test
    void testWorkingEquals() {
        Sub sub1 = new Sub(
                new Const(14),
                new Const(2)
        );

        Sub sub2 = new Sub(
                new Const(14),
                new Const(2)
        );

        assertEquals(sub1, sub2);
    }
    
    @Test
    void test() {
        assertEquals(-11, new Sub(new Const(55), new Const(66)).evaluate());
    }
}