package ru.mail.polis.open.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestForCorrectBracketSequenceChecker {

    @BeforeEach
    void resetCorrectBracketSequenceCheckerFields() {
        CorrectBracketSequenceChecker.reset();
    }

    @Test
    void testReturnTrueFromCheckSequence() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(""));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(null));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("()[]{}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("([])"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[()]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{()}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({{[]}})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({{[]{}}()})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({[](){{}{}()()}})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({[]([]){{}{[]}()()}}){}{()}"));
    }

    @Test
    void testReturnFalseFromCheckSequence() {
        assertFalse(CorrectBracketSequenceChecker.checkSequence("("));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("}("));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("{(})"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([{)])"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({[]}{{()})"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(([])[)]"));
    }

    @Test
    void testThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("([b]{l})"));
        assertThrows(IllegalArgumentException.class, () ->
                assertFalse(CorrectBracketSequenceChecker.checkSequence("()[]{}()[]{}()[]{}()[]{}"
                        + "()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}"
                        + "()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}"
                        + "()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}")));
    }

    @Test
    void testCorrectReturnFromGetSuccessChecksCount() {
        assertEquals(0,
                CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("()[]{}");
        CorrectBracketSequenceChecker.checkSequence("(");
        assertTrue(CorrectBracketSequenceChecker.checkSequence(null));
        CorrectBracketSequenceChecker.checkSequence("{()}");
        assertEquals(3,
                CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("");
        CorrectBracketSequenceChecker.checkSequence("()()()({}]");
        assertEquals(4,
                CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("()()([]{[]}){}");
        assertThrows(IllegalArgumentException.class, () ->
                CorrectBracketSequenceChecker.checkSequence("{}({rr}}{})[[[)"));
        assertEquals(5,
                CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void testCorrectReturnFromGetFailChecksCount() {
        assertEquals(0,
                CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence("{}(((][)))");
        CorrectBracketSequenceChecker.checkSequence("()[]{}");
        CorrectBracketSequenceChecker.checkSequence("{}({}]");
        assertEquals(2,
                CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence("{}({})[]()({})[[[()]]]");
        CorrectBracketSequenceChecker.checkSequence("{}({}()[]()[){})[[[)");
        assertEquals(3,
                CorrectBracketSequenceChecker.getFailChecksCount());

        assertThrows(IllegalArgumentException.class, () ->
                CorrectBracketSequenceChecker.checkSequence("[o]{(m)}{(y)}"));

        assertThrows(IllegalArgumentException.class, () ->
                CorrectBracketSequenceChecker.checkSequence("{}({})()[]{{{[heh}}}{}()"));

        CorrectBracketSequenceChecker.checkSequence("{}({}()[]()))))[)");
        assertEquals(6,
                CorrectBracketSequenceChecker.getFailChecksCount());

        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("{}({.})"));

        assertEquals(7,
                CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void testCorrectReturnFromGetLastSuccessSequence() {
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
        CorrectBracketSequenceChecker.checkSequence(")");
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("()[]{}");
        assertEquals("()[]{}",
                CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("(([)])");
        assertEquals("()[]{}",
                CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("[()({[]}{})]");
        assertEquals("[()({[]}{})]",
                CorrectBracketSequenceChecker.getLastSuccessSequence());

        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("[hello]"));
        assertEquals("[()({[]}{})]",
                CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("[()()()([]{})()({}{[{}][]})]");
        assertEquals("[()()()([]{})()({}{[{}][]})]",
                CorrectBracketSequenceChecker.getLastSuccessSequence());
    }

    @Test
    void testCorrectReset() {
        CorrectBracketSequenceChecker.checkSequence("(");
        CorrectBracketSequenceChecker.checkSequence("");
        CorrectBracketSequenceChecker.checkSequence("()");
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());
        assertEquals(2, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertEquals("()", CorrectBracketSequenceChecker.getLastSuccessSequence());
        CorrectBracketSequenceChecker.reset();
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());
        assertEquals(0, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}