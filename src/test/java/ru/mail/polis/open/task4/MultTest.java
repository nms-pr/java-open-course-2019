package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
class MultTest {

    @Test
    void test() {
        assertEquals(
                78,
                new Mult(
                        new Const(13),
                        new Const(6)
                ).evaluate()
        );
    }

}
