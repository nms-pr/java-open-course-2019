package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExprBuilderImplementsTest {

    ExprBuilderImplements testInstanceOfExprBuilderImplements = new ExprBuilderImplements();

    @Test
    void testMethodsTransformationExpressionWhichReturnCorrectString() {
        assertEquals(
            "2 2 +",
            testInstanceOfExprBuilderImplements.transformationExpression("2 + 2")
        );

        assertEquals(
            "2 2 2 * +",
            testInstanceOfExprBuilderImplements.transformationExpression("2 + 2 * 2")
        );

        assertEquals(
            "2 2 2 3 5 + ^ * +",
            testInstanceOfExprBuilderImplements.transformationExpression("2 + 2 * 2 ^ (3 + 5)")
        );

        assertEquals(
            "2 2 2 3 5 + ^ * + 34 7 5 — / — 5 - 20 + 2 6 2 — * — + 1 —",
            testInstanceOfExprBuilderImplements.transformationExpression("2 + 2 * 2 ^ (3 + 5) — 34 / (7 — 5) + (-5 + 20 — 2 * (6 — 2)) — 1")
        );

        assertEquals(
            "2 2 2 3 5 + ^ * + 34 — 7 5 — 5 - 20 + 2 6 2 — * — + 1 — / -",
            testInstanceOfExprBuilderImplements.transformationExpression("-((2 + 2 * 2 ^ (3 + 5) — 34) / ((7 — 5) + (-5 + 20 — 2 * (6 — 2)) — 1))")
        );
    }

    @Test
    void testThrowOrNotThrowExceptionInMethodsBuild() {
        assertThrows(
            IllegalArgumentException.class,
            () -> testInstanceOfExprBuilderImplements.build(null)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> testInstanceOfExprBuilderImplements.build("")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> testInstanceOfExprBuilderImplements.build(" ")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> testInstanceOfExprBuilderImplements.build("hsnskjl")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> testInstanceOfExprBuilderImplements.build("2 ++ 2")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> testInstanceOfExprBuilderImplements.build("(2 + 2")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> testInstanceOfExprBuilderImplements.build("1 - (2 + 2)")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> testInstanceOfExprBuilderImplements.build(" 2 - 2")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> testInstanceOfExprBuilderImplements.build("(2 + 2) + 34 ^ 2)")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> testInstanceOfExprBuilderImplements.build("-((2 + 2 * 2 ^ (3 + 5) — 34) / ((7 — 5) - (-5 + 20 — 2 * (6 — 2)) — 1))")
        );

        assertDoesNotThrow(() -> testInstanceOfExprBuilderImplements.build("2 + 2"));
        assertDoesNotThrow(() -> testInstanceOfExprBuilderImplements.build("2 + 2 * 34 — 34"));
        assertDoesNotThrow(() -> testInstanceOfExprBuilderImplements.build("(2 + 2) ^ 3 * 3 — 2 ^ 6 "));
        assertDoesNotThrow(() -> testInstanceOfExprBuilderImplements.build("(2 + 2)"));
        assertDoesNotThrow(() -> testInstanceOfExprBuilderImplements.build("-(2 + 2)"));
        assertDoesNotThrow(() -> testInstanceOfExprBuilderImplements.build("-(((2 + 2) ^ 3) + 95 — 3 * 2)"));
        assertDoesNotThrow(() -> testInstanceOfExprBuilderImplements.build("-(((((2 + 2) ^ 3) + 95 — 3 * 2) / 2 + 46) / 4)"));
    }

    @Test
    void testWorkingMethodsBuild() {
        assertEquals(
            new Add(
                new Const(5),
                new Const(5)
            ),
            testInstanceOfExprBuilderImplements.build(
                "5 + 5"
            )
        );

        assertEquals(
            new Add(
                new Sub(
                    new Mult(
                        new Const(5),
                        new Const(2)
                    ),
                    new Const(1)
                ),
                new Const(2)
            ),
            testInstanceOfExprBuilderImplements.build(
                "5 * 2 — 1 + 2"
            )
        );

        assertEquals(
            new Mult(
                new Div(
                    new Const(50),
                    new Const(5)
                ),
                new Sub(
                    new Pow(
                        new Const(10),
                        new Const(2)
                    ),
                    new UnMin(
                        new Add(
                            new Const(5),
                            new Const(15)
                        )
                    )
                )
            ),
            testInstanceOfExprBuilderImplements.build(
                "50 / 5 * (10 ^ 2 — -(5 + 15))"
            )
        );
    }

    @Test
    void testCalculateExpression() {
        assertEquals(
            10,
            testInstanceOfExprBuilderImplements.build(
                "5 + 5"
            ).evaluate()
        );

        assertEquals(
            11,
            testInstanceOfExprBuilderImplements.build(
                "5 * 2 — 1 + 2"
            ).evaluate()
        );

        assertEquals(
            1200,
            testInstanceOfExprBuilderImplements.build(
                "50 / 5 * (10 ^ 2 — -(5 + 15))"
            ).evaluate()
        );

        assertEquals(
            503,
            testInstanceOfExprBuilderImplements.build(
                "2 + 2 * 2 ^ (3 + 5) — 34 / (7 — 5) + (-5 + 20 — 2 * (6 — 2)) — 1"
            ).evaluate()
        );

        assertEquals(
            -30,
            testInstanceOfExprBuilderImplements.build(
                "-(((((2 + 2) ^ 3) + 95 — 3 * 2) / 2 + 46) / 4)"
            ).evaluate()
        );

        assertEquals(
            -60,
            testInstanceOfExprBuilderImplements.build(
                "-((2 + 2 * 2 ^ (3 + 5) — 34) / ((7 — 5) + (-5 + 20 — 2 * (6 — 2)) — 1))"
            ).evaluate()
        );
    }
}