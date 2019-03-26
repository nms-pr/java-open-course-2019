package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
class DivTest {

    @Test
    void test() {
        assertEquals(
                12,
                new Div(
                        new Const(144),
                        new Const(12)
                ).evaluate()
        );
    }

}