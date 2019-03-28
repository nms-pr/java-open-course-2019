package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExprBuilderImplementsTest {

    ExprBuilderImplements testExpr = new ExprBuilderImplements();

    @Test
    void build() {
        assertEquals(new Add(
                            new Const(5),
                            new Const(5)
                    ),
                    testExpr.build("5 + 5"));
        assertEquals(new Sub(
                            new Const(5),
                            new Const(5)
                    ),
                    testExpr.build("5 - 5"));
        assertEquals(
                new Mylt(
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
                testExpr.build(
                        "50 / 5 * (10 ^ 2 - m(5 + 15))"
                )
        );

        assertEquals(
                new Add(
                        new Sub(
                                new Mylt(
                                        new Const(5),
                                        new Const(2)
                                ),
                                new Const(1)
                        ),
                        new Const(2)
                ),
                testExpr.build(
                        "5 * 2 - 1 + 2"
                )
        );
    }

    @Test
    void testBuildEvaluete() {
        assertEquals(5, testExpr.build("5").evaluate());
        assertEquals(10, testExpr.build("8 +  2").evaluate());
        assertEquals(0,testExpr.build("8 +  m8").evaluate());
        assertEquals(25,testExpr.build("(8 +  m3)^2").evaluate());
        assertEquals(16,testExpr.build("(((20 +  m4)-(7+1)))*2").evaluate());
        assertEquals(1200,testExpr.build(
                "50 / 5 * (10 ^ 2 - m(5 + 15))").evaluate());
    }


}