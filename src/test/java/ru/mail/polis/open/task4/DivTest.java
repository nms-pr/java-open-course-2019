package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
class DivTest {

    @Test
    void test() {
        assertEquals(
                2,
                new Div(
                        new Const(10),
                        new Const(5)
                ).evaluate()
        );
    }

}