package ru.mail.polis.open.task4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class AddTest {

    @Test
    void test() {
        assertEquals(3, new Add(new Const(1), new Const(2)).evaluate());
        assertEquals(1, new Add(new Const(-1), new Const(2)).evaluate());
        assertEquals(4, new Add(new Const(2), new Const(2)).evaluate());
        assertEquals(1, new Add(new Const(0), new Const(1)).evaluate());
    }

}
