package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculationTest {
    Calculation calculationTest = new Calculation();

    @Test
    void validCalculation() {
        assertEquals(-1,calculationTest.build("-3+2").evaluate());
        assertEquals(2,calculationTest.build("2*(-3+2)^2").evaluate());
        //assertEquals(0,calculationTest.build("2—2").evaluate());
        //assertEquals(-3,calculationTest.build("2+2—7").evaluate());
        assertEquals(27,calculationTest.build("(1+2)*3^2").evaluate());
        assertEquals(84,calculationTest.build("(1+2)*(1+(3*3^2))").evaluate());
        assertEquals(9,calculationTest.build("(1)*3^(6/3)").evaluate());
        assertEquals(27,calculationTest.build("3^(3)").evaluate());
        assertEquals(9,calculationTest.build("3^(6/3)").evaluate());
        assertEquals(6,calculationTest.build("(1+ 2) *(6/3)").evaluate());
        assertEquals(8,calculationTest.build("(2+2)*2").evaluate());
        assertEquals(6,calculationTest.build("2+2*2").evaluate());
    }

    @Test
    void invalidCalculation() {
        assertThrows(
            IllegalArgumentException.class,
            () -> calculationTest.build(null).evaluate()
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> calculationTest.build("").evaluate()
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> calculationTest.build("2++3").evaluate()
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> calculationTest.build("(2+3))").evaluate()
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> calculationTest.build("(2+34f").evaluate()
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> calculationTest.build("(2+3)/").evaluate()
        );
    }

}
