package ru.mail.polis.open.task4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class MultiplyTest {

    @Test
    void test() {
        assertEquals(1, new Multiply(new Const(1), new Const(1)).evaluate());
        assertEquals(8, new Multiply(new Const(4), new Const(2)).evaluate());
        assertEquals(-2, new Multiply(new Const(2), new Const(-1)).evaluate());
        assertEquals(0, new Multiply(new Const(0), new Const(1)).evaluate());
    }

}
