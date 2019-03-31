package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExprBuilderImplTest {

    private final ExprBuilder exprBuilder = new ExprBuilderImpl();

    @Test
    void inputNullAndEmptyAndUnknown() {
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(null));
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(""));
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build("sdf"));
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build("[5]"));
    }

    @Test
    void addAndSub() {
        String e1 = "+5";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e1));

        String e2 = "5+";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e2));

        String e3 = "5 + 51 +";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e3));

        String e4 = "5 + 51 —— 4";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e4));

        String e5 = "5 +— 51 — 4";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e5));

        String e6 = "5 + 5";
        assertEquals(" Add(Const(5), Const(5))", exprBuilder.build(e6).toString());
        assertEquals(10, exprBuilder.build(e6).evaluate());

        String e7 = "5 + 5 — 4";
        assertEquals(" Add(Const(5),  Sub(Const(5), Const(4)))", exprBuilder.build(e7).toString());
        assertEquals(6, exprBuilder.build(e7).evaluate());
    }

    @Test
    void multOrDiv() {
        String e1 = "*5";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e1));

        String e2 = "5*";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e2));

        String e3 = "5 * 51 *";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e3));

        String e4 = "5 * 51 // 4";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e4));

        String e5 = "5 */ 51 / 4";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e5));

        String e6 = "5 * 5";
        assertEquals(" Mult(Const(5), Const(5))", exprBuilder.build(e6).toString());
        assertEquals(25, exprBuilder.build(e6).evaluate());

        String e7 = "5 * 5 / 4"; //понижение до int и ожидаем 6
        int f = 5 / 4; //ошибка из за рекурсивнго спуска, сначала /, потом *. получаем ошибку округления
        f = 5 * 5;
        f = f / 4;
        assertEquals(" Mult(Const(5),  Div(Const(5), Const(4)))", exprBuilder.build(e7).toString());
        assertEquals(f, exprBuilder.build(e7).evaluate());
//        assertEquals(6, exprBuilder.build(e7).evaluate());
    }

    @Test
    void pow() {
        String e1 = "^5";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e1));

        String e2 = "5^";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e2));

        String e3 = "5 ^ 51 ^";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e3));

        String e4 = "5 ^ 51 ^^ 4";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e4));

        String e5 = "5 ^^ 51 ^ 4";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e5));

        String e6 = "2 ^ 5";
        assertEquals(" Pow(Const(2), Const(5))", exprBuilder.build(e6).toString());
        assertEquals(32, exprBuilder.build(e6).evaluate());

        String e7 = "2 ^ 2 ^ 2";
        assertEquals(" Pow(Const(2),  Pow(Const(2), Const(2)))", exprBuilder.build(e7).toString());
        assertEquals(16, exprBuilder.build(e7).evaluate());
    }

    @Test
    void unary() {
        String e0 = "- -5";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e0));

        String e1 = "- 5";
        assertEquals(" Sub(Const(0), Const(5))", exprBuilder.build(e1).toString());
        assertEquals(-5, exprBuilder.build(e1).evaluate());

        String e2 = "5-";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e2));

        String e3 = "5 - 51 -";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e3));

        String e4 = "5 - 51 -- 4";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e4));

        String e5 = "5 - 51 - 4";
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e5));

        String e6 = "- 5";
        assertEquals(" Sub(Const(0), Const(5))", exprBuilder.build(e6).toString());
        assertEquals(-5, exprBuilder.build(e6).evaluate());

        String e7 = "-2 — 2";
        assertEquals(" Sub( Sub(Const(0), Const(2)), Const(2))", exprBuilder.build(e7).toString());
        assertEquals(-4, exprBuilder.build(e7).evaluate());
    }

    @Test
    void uncorrectExprAllOperation() {
        String e1 = "5 + 51 +";
        String e2 = "* 5 * 2 — 1 + 2";
        String e3 = "5 ** 2 — 1 + 2";
        String e4 = "5 * 2 / 1 ++ 2";
        String e5 = "5 * 2 *— 1 + 2";
        String e6 = "5 * 2 — 1 + 2^";
        String e7 = " +3";
        String e8 = "6 - 3";

        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e1));
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e2));
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e3));
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e4));
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e5));
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e6));
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e7));
        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e8));
    }
//
//    @Test
//    void uncorrectExprBrackets() {
//        String e1 = "(5 + 5";
//        String e2 = "5 + (5";
//        String e3 = ")5 + 5";
//        String e4 = "5 + 5)";
//        String e5 = "5 * 2 (- 1 + 2";
//        String e6 = "5 * 2 ()- 1 + 2";
//        String e7 = "5 (* 2 - 1) + 2";
//        String e8 = "5 * 2 (- 1) + 2";
//        String e9 = "5 * 2 - 1 + (2)";
//        String e10 = "5 * 2 - 1 + (2 ^ 2)";
//
//        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e1));
//        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e2));
//        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e3));
//        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e4));
//        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e5));
//        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e6));
//        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e7));
//        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e8));
//        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e9));
//        assertThrows(IllegalArgumentException.class, () -> exprBuilder.build(e10));
//
//    }
//
//    @Test
//    void correctExpr() {
//        String e1 = "5 + 5";
//        String e2 = "5 * 2 - 1 + 2";
//
//        assertEquals("( Add(Const(5), Const(5))", exprBuilder.build(e1));
//        assertEquals(10, exprBuilder.build(e1).evaluate());
//        assertEquals(" Add( Sub( Mult(Const(5), Const(2)), Const(1)), Const(2))", exprBuilder.build(e2));
//        assertEquals(10, exprBuilder.build(e2).evaluate());
//    }
}