package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DivTest {

    @Test
    void test() {
        assertEquals(
            5,
            new Div(
                new Const(35),
                new Const(7)
            ).evaluate()
        );
    }

    @Test
    void testWorkingEquals() {
        Div div1 = new Div(
            new Const(35),
            new Const(7)
        );

        Div div2 = new Div(
            new Const(15),
            new Const(3)
        );

        assertEquals(div1, div2);
    }
}
