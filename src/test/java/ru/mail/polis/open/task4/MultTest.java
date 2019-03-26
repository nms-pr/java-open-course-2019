package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
class MultTest {

    @Test
    void test() {
        assertEquals(
                32,
                new Mult(
                        new Const(8),
                        new Const(4)
                ).evaluate()
        );
    }

}