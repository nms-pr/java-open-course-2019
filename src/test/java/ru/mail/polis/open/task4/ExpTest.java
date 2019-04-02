package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpTest {

    @Test
    void test() {
        assertEquals(125, new Exponential(new Const(5), new Const(3)).evaluate());
    }

    @Test
    void testWorkEquals() {
        Exponential firstExp = new Exponential(new Const(5), new Const(3));
        Exponential secondExp = new Exponential(new Const(5), new Const(3));
        assertEquals(firstExp, secondExp);
    }
}
