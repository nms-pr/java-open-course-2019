package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnarMinusTest {
    @Test
    void test() {
        assertEquals(
                -3,
                new UnarMinus(new Const(3)).evaluate()
        );
    }

}