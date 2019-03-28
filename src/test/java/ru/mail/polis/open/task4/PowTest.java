package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowTest {

    @Test
    void testEvaluate() {
        assertEquals(25, new Pow(new Const(5), new Const(2)).evaluate());
    }

    @Test
    void testWorkingEquals() {
        Pow pow1 = new Pow(
                new Const(4),
                new Const(2)
        );

        Pow pow2 = new Pow(
                new Const(4),
                new Const(2)
        );

        assertEquals(pow1, pow2);
    }
    
    @Test
    void test() {
        assertEquals(1, new Pow(new Const(55), new Const(0)).evaluate());
    }
}