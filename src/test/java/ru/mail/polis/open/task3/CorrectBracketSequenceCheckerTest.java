package ru.mail.polis.open.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CorrectBracketSequenceCheckerTest {

    @BeforeEach
    void resetCountersOfClassCorrectBracketSequenceChecker() {
        CorrectBracketSequenceChecker.discard();
    }

    @Test
    void testReturnTrue() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(""));
    }

    @Test
    void testReturnFalse() {
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(()"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("("));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("[[[]]["));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("{}}{"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("][]["));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([)]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("{(})()"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(][{{([])}}}])"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("[]{})[){}]{()[]}({}()[])[()]()[{}]]{}"));
    }

    @Test
    void testCorrectWorkingAmountOfSuccessfulAttempts() {
        assertEquals(0,
                CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("(()[]{})");
        CorrectBracketSequenceChecker.checkSequence("([{{{([])}}}])");
        assertEquals(2,
                CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("()()()()()()()()()()()(){}{}{}{}{}{}{}{{}}");
        assertEquals(3,
                CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence(")(");
        assertEquals(3,
                CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void testCorrectWorkingAmountOfFailedAttempts() {
        assertEquals(0,
                CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence("(()[]{})");
        CorrectBracketSequenceChecker.checkSequence("([{{{([])}}}])");
        assertEquals(0,
                CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence("()()()()()()()()()()()(){}{}{}{}{}{}{}{{}}");
        assertEquals(0,
                CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence(")(");
        assertEquals(1,
                CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence(")()[](");
        assertEquals(2,
                CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void testCorrectWorkingGetLastSuccessSequence() {
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
        CorrectBracketSequenceChecker.checkSequence(")(");
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("()()()");
        assertEquals("()()()",
                CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("))){}}}{{}");
        assertEquals("()()()",
                CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("[][][][]({[]})");
        assertEquals("[][][][]({[]})",
                CorrectBracketSequenceChecker.getLastSuccessSequence());

        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("(0)"));
        assertEquals("[][][][]({[]})",
                CorrectBracketSequenceChecker.getLastSuccessSequence());

    }

    @Test
    void testThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("[n]"));
    }
}
