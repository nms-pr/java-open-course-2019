package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
class BracketsTest {

    @Test
    void test() {
        assertEquals(
                5,
                new Brackets(
                        new Const(5)
                ).evaluate()
        );
    }

}


