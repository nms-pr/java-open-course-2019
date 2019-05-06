package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinusTest {

    @Test
    void test() {

        assertEquals(
                10,
                new Minus(
                        new Const(-10)
                ).evaluate()
        );

    }

}
