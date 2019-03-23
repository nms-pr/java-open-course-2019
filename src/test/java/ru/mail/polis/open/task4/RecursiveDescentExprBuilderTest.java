package ru.mail.polis.open.task4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RecursiveDescentExprBuilderTest {

    private static ExprBuilder builder;

    @BeforeAll
    static void builderInit() {
        builder = new RecursuveDescentExprBuilder();
    }

    @Test
    void positiveBuild() {
        assertEquals(10, builder.build("2*3+4").evaluate());
        assertEquals(10, builder.build("(2*3+4)").evaluate());
        assertEquals(14, builder.build("2*(3+4)").evaluate());
        assertEquals(-7, builder.build("(1-2)*(3+4)").evaluate());
        assertEquals(-5, builder.build("-5").evaluate());
        assertEquals(2, builder.build("-4+6").evaluate());
        assertEquals(27, builder.build("3^3").evaluate());
        assertEquals(106, builder.build("42+64").evaluate());
        assertEquals(1, builder.build("(42 +( 64/8) /8)/43").evaluate());
        assertEquals(16, builder.build("2^2^2").evaluate());
        assertEquals(0, builder.build("4+ 2^ 2^ 2/ 16-5").evaluate());
    }

    @Test
    void negativeBuild() {
        assertThrows(IllegalArgumentException.class, () -> builder.build(""));
        assertThrows(IllegalArgumentException.class, () -> builder.build("(3-4"));
        assertThrows(IllegalArgumentException.class, () -> builder.build("-"));
        assertThrows(IllegalArgumentException.class, () -> builder.build("3+"));
        assertThrows(IllegalArgumentException.class, () -> builder.build("3-"));
        assertThrows(IllegalArgumentException.class, () -> builder.build("3*"));
        assertThrows(IllegalArgumentException.class, () -> builder.build("3^"));
        assertThrows(IllegalArgumentException.class, () -> builder.build("()"));
        assertThrows(IllegalArgumentException.class, () -> builder.build("foo+bar"));
    }

    @Test
    void nullBuild() {
        assertThrows(IllegalArgumentException.class, () -> builder.build(null));
    }

}
