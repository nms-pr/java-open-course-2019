package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnaryMinusTest {

    @Test
    void test() {
        assertEquals(-7, new UnaryMinus(7).evaluate());
    }
}
