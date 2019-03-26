package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuilderTest {

    private static final @Nullable String CORRECT_LINE_1 = "((7 + ( 25 - 4 * 5 ) + ( 4 / 2 )))";
    private static final @Nullable String CORRECT_LINE_2 = "7+7+7+7*2";
    private static final @Nullable String CORRECT_LINE_3 = "(2+2)*(4-2)-(2+3*2)-3";
    private static final @Nullable String CORRECT_LINE_4 = "(77+3)*(97-2)-(7+7*2)-3+4^2-4";
    private static final @Nullable String CORRECT_LINE_5 = "((10 - 5) * ((5 - 1) / 2))";
    private static final @Nullable String INCORRECT_LINE_1 = "";
    private static final @Nullable String INCORRECT_LINE_2 = "((2*4() - 5";
    private static final @Nullable String INCORRECT_LINE_3 = null;


    @Test
    void build() {
        Builder correctBuilder1 = new Builder(CORRECT_LINE_1);
        assertEquals(14, correctBuilder1.evaluate());
        Builder correctBuilder2 = new Builder(CORRECT_LINE_2);
        assertEquals(35, correctBuilder2.evaluate());
        Builder correctBuilder3 = new Builder(CORRECT_LINE_3);
        assertEquals(-3, correctBuilder3.evaluate());
        Builder correctBuilder4 = new Builder(CORRECT_LINE_4);
        assertEquals(7588, correctBuilder4.evaluate());
        Builder correctBuilder5 = new Builder(CORRECT_LINE_5);
        assertEquals(10, correctBuilder5.evaluate());
        assertThrows(IllegalArgumentException.class, () -> new Builder(INCORRECT_LINE_1));
        assertThrows(IllegalArgumentException.class, () -> new Builder(INCORRECT_LINE_2));
        assertThrows(IllegalArgumentException.class, () -> new Builder(INCORRECT_LINE_3));
    }
}
