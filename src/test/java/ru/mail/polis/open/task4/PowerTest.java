package ru.mail.polis.open.task4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PowerTest {

    @Test
    void test() {
        assertEquals(1, new Power(new Const(1), new Const(1)).evaluate());
        assertEquals(16, new Power(new Const(4), new Const(2)).evaluate());
        assertEquals(0, new Power(new Const(2), new Const(-1)).evaluate());
        assertEquals(0, new Power(new Const(0), new Const(1)).evaluate());
        assertEquals(27, new Power(new Const(3), new Const(3)).evaluate());
    }

}
