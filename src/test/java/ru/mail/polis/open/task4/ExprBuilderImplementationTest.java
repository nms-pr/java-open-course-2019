package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExprBuilderImplementationTest {

    private ExprBuilderImplementation calculator = new ExprBuilderImplementation();

    @Test
    void validCalculation() {
        assertEquals(24, calculator.build("8/2*6").evaluate());
        assertEquals(14, calculator.build("7*4/2").evaluate());
        assertEquals(29, calculator.build("(1+3)*5+9").evaluate());
        assertEquals(126, calculator.build("(4*3+2)*(4+5)").evaluate());
        assertEquals(40, calculator.build("(2^(1*3)+2)*4").evaluate());
        assertEquals(-1, calculator.build("-7+6").evaluate());
        assertEquals(2, calculator.build("2*(-3+2)^2").evaluate());
        assertEquals(-2, calculator.build("7—9").evaluate());
        assertEquals(-3, calculator.build("2+2—7").evaluate());
        assertEquals(27, calculator.build("(1+2)*3^2").evaluate());
        assertEquals(84, calculator.build("(1+2)*(1+(3*3^2))").evaluate());
        assertEquals(9, calculator.build("(1)*3^(6/3)").evaluate());
        assertEquals(27, calculator.build("3^(3)").evaluate());
        assertEquals(9, calculator.build("3^(6/3)").evaluate());
        assertEquals(6, calculator.build("(1+ 2) *(6/3)").evaluate());
        assertEquals(8, calculator.build("(2+2)*2").evaluate());
        assertEquals(12, calculator.build("5+7*1").evaluate());
    }

    @Test
    void calculationExceptionsTest() {

        assertThrows(
            IllegalArgumentException.class,
            () -> calculator.build("").evaluate()
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> calculator.build("(9+0)/").evaluate()
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> calculator.build("8++4").evaluate()
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> calculator.build(null).evaluate()
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> calculator.build("(6+5))").evaluate()
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> calculator.build("(1+58f").evaluate()
        );

    }

    @Test
    void buildTest() {

        assertEquals(new Const(5), calculator.build("5"));

        assertEquals(
                new Add(
                        new Const(7),
                        new Const(5)),
                calculator.build("7+5")
        );

        assertEquals(
                new Add(
                        new Const(4),
                        new Division(
                                new Const(4),
                                new Power(new Const(8),
                                        new Minus(new Const(1))))),
                calculator.build("4+4/8^(-1)")
        );
    }

}
