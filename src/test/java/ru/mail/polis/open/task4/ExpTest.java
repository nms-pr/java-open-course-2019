package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpTest {

    @Test
    void test() {
        assertEquals(125, new Exponential(new Const(5), new Const(3)).evaluate());
    }
}
