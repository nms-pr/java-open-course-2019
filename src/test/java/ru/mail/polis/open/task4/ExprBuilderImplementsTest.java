package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExprBuilderImplementsTest {

    ExprBuilderImplements ebi = new ExprBuilderImplements();

    @Test
    void testMethodsTransformationExpressionWhichReturnCorrectString() {
        assertEquals(
            "2 2 +",
            ebi.transformationExpression(
                "2 + 2",
                ebi.operationsPriority
            )
        );

        assertEquals(
            "2 2 2 * +",
            ebi.transformationExpression(
               "2 + 2 * 2",
               ebi.operationsPriority
            )
        );

        assertEquals(
            "2 2 2 3 5 + ^ * +",
            ebi.transformationExpression(
                "2 + 2 * 2 ^ (3 + 5)",
                ebi.operationsPriority
            )
        );

        assertEquals(
            "2 2 2 3 5 + ^ * + 34 7 5 — / — 5 - 20 + 2 6 2 — * — + 1 —",
            ebi.transformationExpression(
                "2 + 2 * 2 ^ (3 + 5) — 34 / (7 — 5) + (-5 + 20 — 2 * (6 — 2)) — 1",
                ebi.operationsPriority
            )
        );

        assertEquals(
            "2 2 2 3 5 + ^ * + 34 — 7 5 — 5 - 20 + 2 6 2 — * — + 1 — / -",
            ebi.transformationExpression(
                "-((2 + 2 * 2 ^ (3 + 5) — 34) / ((7 — 5) + (-5 + 20 — 2 * (6 — 2)) — 1))",
                ebi.operationsPriority
            )
        );
    }

    @Test
    void testThrowOrNotThrowExceptionInMethodsBuild() {
        assertThrows(
            IllegalArgumentException.class,
            () -> ebi.build(null)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> ebi.build("")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> ebi.build(" ")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> ebi.build("hsnskjl")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> ebi.build("2 ++ 2")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> ebi.build("(2 + 2")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> ebi.build("1 - (2 + 2)")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> ebi.build(" 2 - 2")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> ebi.build("(2 + 2) + 34 ^ 2)")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> ebi.build("-((2 + 2 * 2 ^ (3 + 5) — 34) / ((7 — 5) - (-5 + 20 — 2 * (6 — 2)) — 1))")
        );

        assertDoesNotThrow(() -> ebi.build("2 + 2"));
        assertDoesNotThrow(() -> ebi.build("2 + 2 * 34 — 34"));
        assertDoesNotThrow(() -> ebi.build("(2 + 2) ^ 3 * 3 — 2 ^ 6 "));
        assertDoesNotThrow(() -> ebi.build("(2 + 2)"));
        assertDoesNotThrow(() -> ebi.build("-(2 + 2)"));
        assertDoesNotThrow(() -> ebi.build("-(((2 + 2) ^ 3) + 95 — 3 * 2)"));
        assertDoesNotThrow(() -> ebi.build("-(((((2 + 2) ^ 3) + 95 — 3 * 2) / 2 + 46) / 4)"));
    }

    @Test
    void testWorkingMethodsBuild() {
        assertEquals(
            new Add(
                new Const(5),
                new Const(5)
            ),
            ebi.build(
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
            ebi.build(
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
            ebi.build(
                "50 / 5 * (10 ^ 2 — -(5 + 15))"
            )
        );
    }

    @Test
    void testCalculateExpression() {
        assertEquals(
            10,
            ebi.build(
                "5 + 5"
            ).evaluate()
        );

        assertEquals(
            11,
            ebi.build(
                "5 * 2 — 1 + 2"
            ).evaluate()
        );

        assertEquals(
            1200,
            ebi.build(
                "50 / 5 * (10 ^ 2 — -(5 + 15))"
            ).evaluate()
        );

        assertEquals(
            503,
            ebi.build(
                "2 + 2 * 2 ^ (3 + 5) — 34 / (7 — 5) + (-5 + 20 — 2 * (6 — 2)) — 1"
            ).evaluate()
        );

        assertEquals(
            -30,
            ebi.build(
                "-(((((2 + 2) ^ 3) + 95 — 3 * 2) / 2 + 46) / 4)"
            ).evaluate()
        );

        assertEquals(
            -60,
            ebi.build(
                "-((2 + 2 * 2 ^ (3 + 5) — 34) / ((7 — 5) + (-5 + 20 — 2 * (6 — 2)) — 1))"
            ).evaluate()
        );
    }
}
