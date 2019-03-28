package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivTest {

    @Test
    void test() {
        assertEquals(5, new Div(new Const(55), new Const(11)).evaluate());
    }

    @Test
    void testEvaluate() {
        assertEquals(-5, new Div(new Const(-55), new Const(11)).evaluate());
    }

    @Test
    void testWorkingEquals() {
        Div div1 = new Div(
                new Const(14),
                new Const(2)
        );

        Div div2 = new Div(
                new Const(14),
                new Const(2)
        );

        assertEquals(div1, div2);
    }

}