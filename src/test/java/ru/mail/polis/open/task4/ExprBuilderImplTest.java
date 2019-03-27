package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
class ExprBuilderImplTest {

    @Test
    void test() {
        int flag = new ExprBuilderImpl().build("6+99/3+8*3").evaluate();

        assertEquals(63, flag);
    }

    @Test
    void testMinus() {
        int flag = new ExprBuilderImpl().build("-6+99/3+8*3").evaluate();

        assertEquals(51, flag);
    }

}
