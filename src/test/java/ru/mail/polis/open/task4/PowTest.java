package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
class PowTest {

    @Test
    void test() {
        assertEquals(
                625,
                new Pow(
                        new Const(25),
                        new Const(2)
                ).evaluate()
        );
    }

}