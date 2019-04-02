package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DivTest {

    @Test
    void test() {
        assertEquals(7, new Divide(new Const(77), new Const(11)).evaluate());
    }
}
