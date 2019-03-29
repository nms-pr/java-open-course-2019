package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    private static Expr expr;

    @Test
    void testAdd() {
        expr = new Addition(new Const(1), new Const(2));
        assertEquals(" Add(Const(1), Const(2))", expr.toString());
        assertEquals(3, new Addition(new Const(1), new Const(2)).evaluate());

        expr = new Addition(new Const(1), new Const(-6));
        assertEquals(" Add(Const(1), Const(-6))", expr.toString());
        assertEquals(-5, expr.evaluate());
    }

    @Test
    void testSub() {
        expr = new Subtraction(new Const(10), new Const(4));
        assertEquals(" Sub(Const(10), Const(4))", expr.toString());
        assertEquals(6, expr.evaluate());

        expr = new Subtraction(new Const(-1), new Const(2));
        assertEquals(" Sub(Const(-1), Const(2))", expr.toString());
        assertEquals(-3, expr.evaluate());
    }

    @Test
    void testMylti() {
        expr = new Multiplication(new Const(2), new Const(4));
        assertEquals(" Mult(Const(2), Const(4))", expr.toString());
        assertEquals(8, expr.evaluate());

        expr = new Multiplication(new Const(34510), new Const(0));
        assertEquals(" Mult(Const(34510), Const(0))", expr.toString());
        assertEquals(0, expr.evaluate());
    }

    @Test
    void testDiv() {
        expr = new Division(new Const(323), new Const(0));
        assertEquals(" Div(Const(323), Const(0))", expr.toString());
        assertThrows(ArithmeticException.class, () -> expr.evaluate());

        expr = new Division(new Const(6), new Const(3));
        assertEquals(" Div(Const(6), Const(3))", expr.toString());
        assertEquals(2, expr.evaluate());
    }

    @Test
    void testPow() {
        expr = new Exponentiation(new Const(2), new Const(5));
        assertEquals(" Pow(Const(2), Const(5))", expr.toString());
        assertEquals(32, expr.evaluate());

        expr = new Exponentiation(new Const(234), new Const(0));
        assertEquals(" Pow(Const(234), Const(0))", expr.toString());
        assertEquals(1, expr.evaluate());
    }

}