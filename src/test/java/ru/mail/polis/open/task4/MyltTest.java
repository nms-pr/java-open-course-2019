package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyltTest {

    @Test
    void test() {
        assertEquals(55, new Mylt(new Const(5), new Const(11)).evaluate());
    }

    @Test
    void testWorkingEquals() {
        Mylt mylt1 = new Mylt(
                new Const(14),
                new Const(2)
        );

        Mylt mylt2 = new Mylt(
                new Const(14),
                new Const(2)
        );

        assertEquals(mylt1, mylt2);
    }
    
    @Test
    void testEvaluate() {
        assertEquals(0, new Mylt(new Const(55), new Const(0)).evaluate());
    }
}