package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PostFixAndThrowTest {
    ExprBuilderImplements testExpr = new ExprBuilderImplements();

    @Test
    void testExpressionToPostFix() {
        assertEquals("5 5 +", testExpr.expressionToPostFix("5+  5"));
        assertEquals("5 5 -", testExpr.expressionToPostFix("5  -5"));
        assertEquals("5 5 *", testExpr.expressionToPostFix("5 * 5"));
        assertEquals("5 5 /", testExpr.expressionToPostFix("5 /  5"));
        assertEquals("5 5 ^", testExpr.expressionToPostFix("5 ^  5"));
        assertEquals(" 5 m 2 -", testExpr.expressionToPostFix(" m5 - 2 "));
        assertEquals("6 2 / 9 3 2 ^ / - 4 6 * + 8 +",
                testExpr.expressionToPostFix("(6/2 - 9/3^2 + 4*6) + 8"));
        assertEquals("1 5 5 ^ 25 / +",
                testExpr.expressionToPostFix(" 1 + 5 ^ 5 / 25"));
        assertEquals(" 10 m 5 1 + 2 / - 2 *",
                testExpr.expressionToPostFix("(  (m10) - (5+1) / 2)  * 2 "));
    }

    @Test
    void testThrowBuild() {
        assertThrows(IllegalArgumentException.class, () -> testExpr.build(null));
        assertThrows(IllegalArgumentException.class, () -> testExpr.build(""));
        assertThrows(IllegalArgumentException.class, () -> testExpr.build(" "));
        assertThrows(IllegalArgumentException.class, () -> testExpr.build("5 w - 5"));
        assertThrows(IllegalArgumentException.class, () -> testExpr.build("5(m5)"));
        assertThrows(IllegalArgumentException.class, () -> testExpr.build("5 + 5 )"));
        assertThrows(IllegalArgumentException.class, () -> testExpr.build("1 + (5 - 5))"));
        assertThrows(IllegalArgumentException.class, () -> testExpr.build("(5 - 5 "));
        assertThrows(IllegalArgumentException.class, () -> testExpr.build("((5- 5) + 1"));
        assertThrows(IllegalArgumentException.class, () -> testExpr.build("5 - 5 - "));

    }
}
