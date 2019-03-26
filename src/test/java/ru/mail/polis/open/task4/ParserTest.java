package ru.mail.polis.open.task4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    private static Parser parser;

    @BeforeAll
    static void parserInit() {
        parser = new Parser();
    }

    @Test
    void build() {
        assertEquals(3, parser.build("2-3+4").evaluate());
        assertEquals(5, parser.build("(2*3-1)").evaluate());
        assertEquals(5, parser.build("--5").evaluate());
        assertEquals(81, parser.build("9^2").evaluate());
        assertEquals(81, parser.build("3^2^2").evaluate());
        assertEquals(12, parser.build("3*2^2").evaluate());
        assertEquals(-2, parser.build("-(2)").evaluate());
        assertEquals(2, parser.build("-(-2)").evaluate());
        assertEquals(144, parser.build("(3*2^2+1/5*10)^2").evaluate());

        assertThrows(IllegalArgumentException.class, () -> parser.build(""));
        assertThrows(IllegalArgumentException.class, () -> parser.build(null));
        assertThrows(IllegalArgumentException.class, () -> parser.build("+"));
        assertThrows(IllegalArgumentException.class, () -> parser.build("+-*/"));
        assertThrows(IllegalArgumentException.class, () -> parser.build("(4-4"));
        assertThrows(IllegalArgumentException.class, () -> parser.build("1-2)"));
        assertThrows(IllegalArgumentException.class, () -> parser.build("(1-2))"));




    }
}