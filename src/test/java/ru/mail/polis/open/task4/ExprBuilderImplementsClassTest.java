package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExprBuilderImplementsClassTest {

    ExprBuilderImplementClass builderObject = new ExprBuilderImplementClass();

    @Test
    void testThrowException() {
        assertThrows(IllegalArgumentException.class, () -> builderObject.build(""));
        assertThrows(IllegalArgumentException.class, () -> builderObject.build(null));
        assertThrows(IllegalArgumentException.class, () -> builderObject.build("words"));
        assertThrows(IllegalArgumentException.class, () -> builderObject.build("++8"));
        assertThrows(IllegalArgumentException.class, () -> builderObject.build("(1 * 5 - 2"));
        assertThrows(IllegalArgumentException.class, () -> builderObject.build("(1) ++ 1"));
        assertThrows(IllegalArgumentException.class, () -> builderObject.build("((1 + 2)"));
    }

    @Test
    void testNotThrowExceprion() {
        assertDoesNotThrow(() -> builderObject.build("7 + 7"));
        assertDoesNotThrow(() -> builderObject.build(" 1 * 2 ─ 1"));
        assertDoesNotThrow(() -> builderObject.build("3 ─ 2 ^ 8 / 1 + 7"));
    }

    @Test
    void testCalculatingExpression() {
        assertEquals(10, builderObject.build("7 + 3").evaluate());
    }

    @Test
    void correctExpressionTest() {
        assertEquals(8,
            builderObject.build("(-1 * (3 + 1)) ^ 2 / 2").evaluate());
    }
}
