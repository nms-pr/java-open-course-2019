package ru.mail.polis.open.task3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConsoleFizzBuzzGameTest {

    @Test
    void testNullLastSequence() {
        String result = CorrectBracketSequenceChecker.getLastSuccessSequence();

        assertNull(result);
    }

    @Test
    void testEmptySequence() {
        String input = "";
        boolean result = CorrectBracketSequenceChecker.checkSequence(input);

        assertTrue(result);
    }

    @Test
    void testGoodSequence() {
        String input = "({[[()]]})";
        boolean result = CorrectBracketSequenceChecker.checkSequence(input);

        assertTrue(result);
    }

    @Test
    void testBadSequence_Wrong() {
        String input = "({[[()})})";
        boolean result = CorrectBracketSequenceChecker.checkSequence(input);

        assertFalse(result);
    }

    @Test
    void testBadSequence_NotBracerSymbol() {
        String input = "({[[()]]a})";
        boolean result = CorrectBracketSequenceChecker.checkSequence(input);

        assertFalse(result);
    }

    @Test
    void testCorrectSequenceCount() {
        String input = "({[]})";
        int counter = 0;

        for(int i = 0; i < 10; i++){
            if(CorrectBracketSequenceChecker.checkSequence(input))
                counter++;
        }

        assertEquals(10,counter);
    }

    @Test
    void testWrongSequenceCount() {
        String input = "))))";// "{(fwef", "({[]})", "()))""({[])"
        int counter = 0;

        for(int i = 0; i < 10; i++){
            if(!CorrectBracketSequenceChecker.checkSequence(input))
                counter++;
        }

        assertEquals(10,counter);
    }
}