package ru.mail.polis.open.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class CorrectBracketSequenceCheckerTest {

    private static final String SIMPLE_CORRECT = "()";
    private static final String SIMPLE_WRONG = "())";
    private static final String CLOSE_WRONG = "]";
    private static final String EMPTY = "";
    private static final String SYMBOLS_WRONG = "()1[]";
    private static final String NEAR_CORRECT = "()[]{}";
    private static final String SPACE_WRONG = " ";
    private static final String MORE_SYMBOLS_WRONG = "(f)1231";
    private static final String SPACE_BETWEEN_WRONG = "() ()";
    private static final String INNER_CORRECT = "(())";
    private static final String COMPLICATED_INNER_CORRECT = "({}[])";
    private static final String MORE_COMPLICATED_INNER_CORRECT = "({}[])({}[])";
    private static final String HUGE_WRONG = "({}[])({}[])({}[])({}[])({}[])({}[])"
        + "({}[])({}[])({}[])({}[])({}[])({}[])({}[])({}[])({}[])"
        + "({}[])({}[])({}[])({}[])({}[])({}[])({}[])({}[])({}[])";

    @BeforeEach
    void refresh() {
        CorrectBracketSequenceChecker.refreshStatistics();
    }

    @Test
    void checkSequence() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(SIMPLE_CORRECT));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(SIMPLE_WRONG));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(CLOSE_WRONG));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(EMPTY));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(SYMBOLS_WRONG));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(NEAR_CORRECT));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(SPACE_WRONG));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(SPACE_BETWEEN_WRONG));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(INNER_CORRECT));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(COMPLICATED_INNER_CORRECT));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(MORE_COMPLICATED_INNER_CORRECT));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(MORE_SYMBOLS_WRONG));
        assertThrows(
            IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence(HUGE_WRONG)
        );
    }

    @Test
    void getSuccessChecksCount() {
        assertEquals(0, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence(SIMPLE_CORRECT);
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence(SIMPLE_WRONG);
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence(CLOSE_WRONG);
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence(EMPTY);
        assertEquals(2, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void getFailChecksCount() {
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence(SIMPLE_CORRECT);
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence(SIMPLE_WRONG);
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence(CLOSE_WRONG);
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence(EMPTY);
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void getLastSuccessSequence() {
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
        CorrectBracketSequenceChecker.checkSequence(SIMPLE_CORRECT);
        assertEquals(SIMPLE_CORRECT, CorrectBracketSequenceChecker.getLastSuccessSequence());
        assertFalse(CorrectBracketSequenceChecker.checkSequence(SIMPLE_WRONG));
        assertEquals(SIMPLE_CORRECT, CorrectBracketSequenceChecker.getLastSuccessSequence());
        assertFalse(CorrectBracketSequenceChecker.checkSequence(CLOSE_WRONG));
        assertEquals(SIMPLE_CORRECT, CorrectBracketSequenceChecker.getLastSuccessSequence());
        assertTrue(CorrectBracketSequenceChecker.checkSequence(EMPTY));
        assertEquals(EMPTY, CorrectBracketSequenceChecker.getLastSuccessSequence());
    }

    @Test
    void checkTwoOpenClose() {
        assertTrue(CorrectBracketSequenceChecker.checkTwoOpenClose('(', ')'));
        assertTrue(CorrectBracketSequenceChecker.checkTwoOpenClose('[', ']'));
        assertTrue(CorrectBracketSequenceChecker.checkTwoOpenClose('{', '}'));
        assertFalse(CorrectBracketSequenceChecker.checkTwoOpenClose('{', ')'));
        assertFalse(CorrectBracketSequenceChecker.checkTwoOpenClose('(', ']'));
        assertFalse(CorrectBracketSequenceChecker.checkTwoOpenClose('(', '('));
        assertFalse(CorrectBracketSequenceChecker.checkTwoOpenClose('(', '{'));
        assertFalse(CorrectBracketSequenceChecker.checkTwoOpenClose(')', ')'));
        assertFalse(CorrectBracketSequenceChecker.checkTwoOpenClose(')', '('));
        assertFalse(CorrectBracketSequenceChecker.checkTwoOpenClose(')', '['));

    }

    @Test
    void checkTwoInners() {
        assertTrue(CorrectBracketSequenceChecker.checkTwoInners('(', '('));
        assertTrue(CorrectBracketSequenceChecker.checkTwoInners('(', '['));
        assertTrue(CorrectBracketSequenceChecker.checkTwoInners('(', '{'));
        assertFalse(CorrectBracketSequenceChecker.checkTwoInners('(', ')'));
        assertFalse(CorrectBracketSequenceChecker.checkTwoInners('(', ']'));
        assertFalse(CorrectBracketSequenceChecker.checkTwoInners('(', '}'));
        assertFalse(CorrectBracketSequenceChecker.checkTwoInners('}', '{'));
        assertFalse(CorrectBracketSequenceChecker.checkTwoInners(')', '{'));
        assertFalse(CorrectBracketSequenceChecker.checkTwoInners(']', '{'));
        assertTrue(CorrectBracketSequenceChecker.checkTwoInners('{', '('));
        assertTrue(CorrectBracketSequenceChecker.checkTwoInners('{', '['));
        assertTrue(CorrectBracketSequenceChecker.checkTwoInners('[', '['));
    }

    @Test
    void refreshStatistics() {
        CorrectBracketSequenceChecker.checkSequence(SIMPLE_CORRECT);
        CorrectBracketSequenceChecker.checkSequence(CLOSE_WRONG);
        CorrectBracketSequenceChecker.checkSequence(COMPLICATED_INNER_CORRECT);
        CorrectBracketSequenceChecker.checkSequence(MORE_COMPLICATED_INNER_CORRECT);
        assertEquals(3, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());
        assertEquals(MORE_COMPLICATED_INNER_CORRECT, CorrectBracketSequenceChecker.getLastSuccessSequence());
        CorrectBracketSequenceChecker.refreshStatistics();
        assertEquals(0, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}