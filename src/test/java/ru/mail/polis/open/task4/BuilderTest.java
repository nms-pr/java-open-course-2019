package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BuilderTest {

    @Test
    void build() {
        Builder builder = new Builder();

        assertEquals(324, builder.build("(1*2^3+5/5*10)^2").evaluate());
        assertEquals(-5, builder.build("9-7-8+1").evaluate());
        assertEquals(15, builder.build("(2*9-3)").evaluate());
        assertEquals(7, builder.build("--7").evaluate());
        assertEquals(1, builder.build("1^2^0").evaluate());
        assertEquals(24, builder.build("3*2^3").evaluate());
        assertEquals(-115, builder.build("-(115)").evaluate());


        assertThrows(IllegalArgumentException.class, () -> builder.build(""));
        assertThrows(IllegalArgumentException.class, () -> builder.build(null));
        assertThrows(IllegalArgumentException.class, () -> builder.build("+-/**/-"));
        assertThrows(IllegalArgumentException.class, () -> builder.build("((((((((((1-9"));
        assertThrows(IllegalArgumentException.class, () -> builder.build("(0-2)))"));
        assertThrows(IllegalArgumentException.class, () -> builder.build(" "));
    }
}