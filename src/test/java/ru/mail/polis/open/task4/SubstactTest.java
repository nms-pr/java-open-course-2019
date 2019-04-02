package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubstactTest {

    @Test
    void test() {
        assertEquals(10, new Substract(new Const(17), new Const(7)).evaluate());
    }
}
