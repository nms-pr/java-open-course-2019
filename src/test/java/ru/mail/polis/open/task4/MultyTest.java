package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultyTest {

    @Test
    void test() {
        assertEquals(77, new Multiplication(new Const(7), new Const(11)).evaluate());
    }
}
