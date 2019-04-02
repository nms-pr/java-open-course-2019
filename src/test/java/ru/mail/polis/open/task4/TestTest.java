package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//todo
class TestTest {
    ExprBuilderImplementClass expressionTest = new ExprBuilderImplementClass();

    @Test
    void correctTest() {
        assertEquals(132,expressionTest.build("(-1+(2+1)^(2+1)*5—6/3)").evaluate());
    }

    @Test
    void spaceTest() {
        assertEquals(37,expressionTest.build(" - 1 + 2 ^ ( 2 + 1 ) * 5 — 6 / 3  ").evaluate());
    }

    @Test
    void nullTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            expressionTest.build(null).evaluate();
        });
    }

    @Test
    void emptyTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            expressionTest.build("").evaluate();
        });
    }

    @Test
    void wrongBracketTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            expressionTest.build("(4))").evaluate();
        });
    }

    @Test
    void wrongSymbolTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            expressionTest.build("4+f").evaluate();
        });
    }

    @Test
    void wrongSignsTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            expressionTest.build("4+5/").evaluate();
        });
    }
}
