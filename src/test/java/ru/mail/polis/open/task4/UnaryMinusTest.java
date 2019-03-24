package ru.mail.polis.open.task4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class UnaryMinusTest {

    @Test
    void test() {
        assertEquals(-1, new UnaryMinus(new Const(1)).evaluate());
        assertEquals(1, new UnaryMinus(new Const(-1)).evaluate());
        assertEquals(-2, new UnaryMinus(new Const(2)).evaluate());
        assertEquals(0, new UnaryMinus(new Const(0)).evaluate());
    }

}
