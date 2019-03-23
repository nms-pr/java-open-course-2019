package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
class ConstTest {

    @Test
    void test() {
        assertEquals(
            5,
            new Const(
                5
            ).evaluate()
        );
    }

}