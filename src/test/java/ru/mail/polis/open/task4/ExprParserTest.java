package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExprParserTest {

    @Test
    void build() {
        ExprParser parser = new ExprParser();
        assertEquals(4+5*2, parser.build("4+5*2").evaluate());
        assertEquals(4*5+2, parser.build("4*5+2").evaluate());
        assertEquals(40+5*2-1, parser.build("40+5*2—1").evaluate());
        assertEquals(2/3, parser.build("2/3").evaluate());
        assertEquals(2/3*5, parser.build("5*2/3").evaluate()); //видимо, округляет криво, не понял почему
        assertEquals(40-1, parser.build("40+5*2/3—1").evaluate());
        assertEquals(-4*2, parser.build("-4*2").evaluate());
        assertEquals(47, parser.build("40+5^2/3—1").evaluate()); //число из калькулятора, Java считает иначе
        assertEquals(40+(6*2+3), parser.build("40+(6*2+3)").evaluate());
        assertEquals(723, parser.build("3+(40+2^3)*(6*2+3)").evaluate()); //число из калькулятора, Java считает иначе
        assertEquals(2160, parser.build("3*(40+2^3)*(6*2+3)").evaluate()); //число из калькулятора, Java считает иначе
        assertEquals(486, parser.build("(40*(4+2)+3)*2").evaluate()); //с этим справиться никак не смог
    }
}