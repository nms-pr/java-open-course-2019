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
        assertThrows(IllegalArgumentException.class, () -> new ExprBuilderImpl().build("(0-1)(2   -5)+10"));
        assertEquals(-1, new ExprBuilderImpl().build("-8 + 7").evaluate());
        assertEquals(2, new ExprBuilderImpl().build("20/2").evaluate());
        assertThrows(IllegalArgumentException.class, () -> new ExprBuilderImpl().build("*9"));
        assertEquals(-8, new ExprBuilderImpl().build("-8").evaluate());
        assertEquals(3, new ExprBuilderImpl().build("5 - 2").evaluate());

    }

}