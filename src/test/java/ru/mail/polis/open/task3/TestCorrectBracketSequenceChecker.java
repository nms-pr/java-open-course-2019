package ru.mail.polis.open.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestCorrectBracketSequenceChecker {
    @BeforeEach
    void resetCorrectBracketSequenceCheckerFields() {
        CorrectBracketSequenceChecker.reset();
    }

    @Test
    void testCheckSequenceTrue() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(""));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[]()(){}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[]{()}{{()}}()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[{}]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[()]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{()}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({{[]}})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({{[]}})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({{[]{}}()})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({[](){{}{}()()}})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({[]([]){{}{[]}()()}}){}{()}"));
    }

    @Test
    void testCheckSequenceFalse() {
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(({}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({}{{({)}{)[(])"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([[[{}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([[[]]]))"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([[[{}({}]{)"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([{}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([{)]}"));
    }

    @Test
    void testThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("(a)"));
        assertThrows(IllegalArgumentException.class, () ->
                assertFalse(CorrectBracketSequenceChecker.checkSequence("()[]{}()[]{}()[]{}()[]{}"
                        + "()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}"
                        + "()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}"
                        + "()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}")));
    }

    @Test
    void testGetSuccessChecksCount() {
        CorrectBracketSequenceChecker.checkSequence("(())");
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("{(({}[]))}");
        assertEquals(2, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("{}");
        assertEquals(3, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("({{[]}})");
        assertEquals(4, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("");
        assertEquals(5, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void testGetFailChecksCount() {
        CorrectBracketSequenceChecker.checkSequence("(}");
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("(}}");
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("(((}}");
        assertEquals(3, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("()}}");
        assertEquals(4, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("[{(])}}");
        assertEquals(5, CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void testGetLastSuccessSequence() {
        CorrectBracketSequenceChecker.checkSequence("()");
        assertEquals("()", CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("()}");
        assertEquals("()", CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}
