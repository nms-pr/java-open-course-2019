package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExprBuilderImplTest {
    @Test
    void test() {
        assertThrows(IllegalArgumentException.class, () -> new ExprBuilderImpl().build(null));
        assertThrows(IllegalArgumentException.class, () -> new ExprBuilderImpl().build("77-#"));
        assertThrows(IllegalArgumentException.class, () -> new ExprBuilderImpl().build("(77 - 66*9"));
        assertThrows(IllegalArgumentException.class, () -> new ExprBuilderImpl().build("         "));
        assertThrows(IllegalArgumentException.class, () -> new ExprBuilderImpl().build("*9"));
        assertEquals(10, new ExprBuilderImpl().build("20/2").evaluate());
        assertEquals(3, new ExprBuilderImpl().build("5 - 2").evaluate());
        assertEquals(11, new ExprBuilderImpl().build("12 - 3 + 4/2").evaluate());
        assertEquals(5, new ExprBuilderImpl().build("12 - 3*(7-4) + 4/2").evaluate());
        assertEquals(-8, new ExprBuilderImpl().build("M8").evaluate());



    }

}