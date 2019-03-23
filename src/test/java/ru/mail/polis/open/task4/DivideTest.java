package ru.mail.polis.open.task4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class DivideTest {

    @Test
    void test() {
        assertEquals(1, new Divide(new Const(1), new Const(1)).evaluate());
        assertEquals(2, new Divide(new Const(4), new Const(2)).evaluate());
        assertEquals(-2, new Divide(new Const(2), new Const(-1)).evaluate());
        assertEquals(0, new Divide(new Const(0), new Const(1)).evaluate());
    }

}
