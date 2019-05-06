package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnMinusTest {

    @Test
    void unMinusTest() {
        assertEquals(
                -2,
                new UnMinus(
                        2).evaluate()
        );
    }
}