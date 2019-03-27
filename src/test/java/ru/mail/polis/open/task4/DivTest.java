package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//todo
class DivTest {

    @Test
    void test() {
        assertEquals(
                9,
                new Div(
                        new Const(27),
                        new Const(3)
                ).evaluate()
        );
    }

    @Test
    void testZero() {
        assertThrows(
                ArithmeticException.class,
                () -> new Div(
                        new Const(27),
                        new Const(0)
                ).evaluate()
        );
    }

}