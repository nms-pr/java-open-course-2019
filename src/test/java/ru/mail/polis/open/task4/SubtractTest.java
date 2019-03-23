package ru.mail.polis.open.task4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SubtractTest {

    @Test
    void test() {
        assertEquals(-1, new Subtract(new Const(1), new Const(2)).evaluate());
        assertEquals(-4, new Subtract(new Const(-2), new Const(2)).evaluate());
        assertEquals(0, new Subtract(new Const(0), new Const(0)).evaluate());
        assertEquals(5, new Subtract(new Const(5), new Const(0)).evaluate());
    }

}
