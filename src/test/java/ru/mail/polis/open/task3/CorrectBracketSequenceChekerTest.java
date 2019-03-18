package ru.mail.polis.open.task3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CorrectBracketSequenceChekerTest {

    @Test
    void testFirstCheckLastBrackString() {
        CorrectBracketSequenceChecker.reset();
        String last = CorrectBracketSequenceChecker.getLastSuccessSequence();

        assertNull(last);
    }

    @Test
    void testCheckNullBrackString() {
        CorrectBracketSequenceChecker.reset();
        String[] input = new String[] {"(((())", "()()(()()", "((((((())))))(", "(()(()())", "(((()))"};

        for (int i = 0; i < input.length; i++) {
            CorrectBracketSequenceChecker.checkSequence(input[i]);
        }

        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
    }

    @Test
    void testEmptyStringIsCorrectBrackString() {
        CorrectBracketSequenceChecker.reset();
        String input = "";

        boolean flag = CorrectBracketSequenceChecker.checkSequence(input);

        assertTrue(flag);
    }

    @Test
    void testSinglIsCorrectBrackString() {
        CorrectBracketSequenceChecker.reset();
        String input = "((((()()))))";

        boolean flag = CorrectBracketSequenceChecker.checkSequence(input);

        assertTrue(flag);
    }

    @Test
    void testSinglIsFailBrackString() {
        CorrectBracketSequenceChecker.reset();
        String input = "(((())";

        boolean flag = CorrectBracketSequenceChecker.checkSequence(input);

        assertFalse(flag);
    }

    @Test
    void testException() {
        CorrectBracketSequenceChecker.reset();
        String input = "((((9))";
        String inputSecond = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32";


        assertThrows(
            IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence(input)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence(inputSecond)
        );
    }

    @Test
    void testManyBrackString() {
        CorrectBracketSequenceChecker.reset();
        String[] input = new String[] {"(((())", "()()()()", "((((((())))))(", "()()()", "((()))"};

        for (int i = 0; i < input.length; i++) {
            CorrectBracketSequenceChecker.checkSequence(input[i]);
        }

        assertEquals(3, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());
    }
}