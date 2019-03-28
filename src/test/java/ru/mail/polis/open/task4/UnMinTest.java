package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnMinTest {

    @Test
    void testEvaluate() {
        assertEquals(-55, new UnMin(new Const(55)).evaluate());
    }

    @Test
    void testWorkingEquals() {
        UnMin unMin1 = new UnMin(new Const(14));
        UnMin unMin2 = new UnMin(new Const(14));

        assertEquals(unMin1, unMin2);
    }
    
}