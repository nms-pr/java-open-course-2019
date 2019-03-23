package ru.mail.polis.open.task4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ConstTest {
    @Test
    void test() {
        assertEquals(5, new Const(5).evaluate());
        assertEquals(0, new Const(0).evaluate());
        assertEquals(999, new Const(999).evaluate());
    }

}
