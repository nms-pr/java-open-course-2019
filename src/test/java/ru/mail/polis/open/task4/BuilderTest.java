package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BuilderTest {

    @Test
    void test() {
        assertThrows(IllegalArgumentException.class, () -> new Builder().build(null));
        assertEquals(-12, new Builder().build("(-  12)").evaluate());
        assertEquals(3, new Builder().build("3").evaluate());
        assertThrows(IllegalArgumentException.class, () -> new Builder().build("12 + 48 - A"));
        assertThrows(IllegalArgumentException.class, () -> new Builder().build("12) + 47- (34-70)"));
        assertThrows(IllegalArgumentException.class, () -> new Builder().build("(13 - 24) (45-40 )+10"));
        assertThrows(IllegalArgumentException.class, () -> new Builder().build("48 - ( -)"));
        assertThrows(IllegalArgumentException.class, () -> new Builder().build("12 + 47 - ((13 - 4)*5"));
        assertEquals(19, new Builder().build("15 + (12 - 11)* 4").evaluate());
        assertEquals(38, new Builder().build("4/2  *(15 + (12 - 11)* 4)").evaluate());
        assertEquals(20, new Builder().build("-5*3 + 40 - (5+ (2*4 - 2^3))").evaluate());
        assertEquals(13, new Builder().build("-(-8- 31)/ (3 * 5 - 4^2 + 4) - (48 - 4 * 6 * 2^1)").evaluate());
        assertEquals(-4, new Builder().build("-(-(-4))").evaluate());
        assertEquals(39, new Builder().build("-(-8 -31)").evaluate());
        assertEquals(-2, new Builder().build("-400/10/2/5/2").evaluate());
        assertEquals(3, new Builder().build("-400/10/2/5/2 + 5").evaluate());
    }
}