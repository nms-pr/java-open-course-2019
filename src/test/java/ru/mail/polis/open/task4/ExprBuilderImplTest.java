package ru.mail.polis.open.task4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExprBuilderImplTest {

    private static ExprBuilderImpl builder;

    @BeforeAll
    static void builderInit() {
        builder = new ExprBuilderImpl();
    }

    @Test
    void build_ThrowsOnIncorrectString() {
        assertThrows(IllegalArgumentException.class, () -> builder.build(""));
        assertThrows(IllegalArgumentException.class, () -> builder.build("(3-4"));
        assertThrows(IllegalArgumentException.class, () -> builder.build("-"));
        assertThrows(IllegalArgumentException.class, () -> builder.build("3-"));
        assertThrows(IllegalArgumentException.class, () -> builder.build("()"));
        assertThrows(IllegalArgumentException.class, () -> builder.build("asd+bas"));
    }

    @Test
    void build_DoesNotThrowOnCorrectString() {
        assertDoesNotThrow(() -> builder.build("1+2"));
        assertDoesNotThrow(() -> builder.build("1 +2"));
        assertDoesNotThrow(() -> builder.build("1+ 2"));
        assertDoesNotThrow(() -> builder.build(" 1+2 "));
        assertDoesNotThrow(() -> builder.build("(1+2)"));
        assertDoesNotThrow(() -> builder.build("1*(1+2)"));
        assertDoesNotThrow(() -> builder.build("-3"));
        assertDoesNotThrow(() -> builder.build("--3"));
        assertDoesNotThrow(() -> builder.build("-3+5^2"));
        assertDoesNotThrow(() -> builder.build("-(9)"));
        assertDoesNotThrow(() -> builder.build("-(4+6)"));
        assertDoesNotThrow(() -> builder.build("42+64"));
    }

    @Test
    void build_BuildsCorrectly() {
        assertEquals(
                new Add(
                        new Multiply(
                                new Const(2),
                                new Const(3)
                        ),
                        new Const(4)
                ),

                builder.build("2*3+4")
        );

        assertEquals(
            new Add(
                new Multiply(
                    new Const(2),
                    new Const(3)
                ),
                new Const(4)
            ),

            builder.build("(2*3+4)")
        );

        assertEquals(
            new Multiply(
                new Const(2),
                new Add(
                    new Const(3),
                    new Const(4)
                )
            ),

            builder.build("2*(3+4)")
        );

        assertEquals(
            new Multiply(
                new Subtract(
                    new Const(1),
                    new Const(2)
                ),
                new Add(
                    new Const(3),
                    new Const(4)
                )
            ),

            builder.build("(1-2)*(3+4)")
        );

        assertEquals(
            new UnaryMinus(
                new Const(5)
            ),

            builder.build("-5")
        );

        assertEquals(
            new UnaryMinus(
                new UnaryMinus(
                    new Const(5)
                )
            ),
            builder.build("--5")
        );

        assertEquals(
            new UnaryMinus(
                new Const(5)
            ),

            builder.build("-(5)")
        );

        assertEquals(
            new UnaryMinus(
                new Add(
                    new Const(4),
                    new Const(6)
                )
            ),

            builder.build("-(4+6)")
        );

        assertEquals(
            new Power(
                new Const(3),
                new Const(2)
            ),
            builder.build("3^2")
        );

        assertEquals(
            new Add(
                new Const (42),
                new Const (64)
            ),
            builder.build("42+64")
        );
    }
}