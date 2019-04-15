package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SolutionExprTest {

    private static @Nullable String FIRST_CORRECT = "2+2*2";
    private static @Nullable String SECOND_CORRECT = "(2+2)^2*2";
    private static @Nullable String THIRD_CORRECT = "(742-42)/100^2";
    private static @Nullable String FIRST_INCORRECT = "(336%";
    private static @Nullable String SECOND_INCORRECT = "563*6+4/0";
    private static @Nullable String NULL = null;
    private static @Nullable String EMPTY = "";

    @Test
    void build() {
        SolutionExpr builder = new SolutionExpr();
        assertEquals(6, builder.build(FIRST_CORRECT).evaluate());
        assertEquals(32, builder.build(SECOND_CORRECT).evaluate());
        assertEquals(49,builder.build(THIRD_CORRECT).evaluate());
        assertThrows(IllegalArgumentException.class, () -> builder.build(FIRST_INCORRECT).evaluate());
        assertThrows(IllegalArgumentException.class, () -> builder.build(SECOND_INCORRECT).evaluate());
        assertThrows(IllegalArgumentException.class, () -> builder.build(NULL).evaluate());
        assertThrows(IllegalArgumentException.class, () -> builder.build(EMPTY).evaluate());
    }
}
