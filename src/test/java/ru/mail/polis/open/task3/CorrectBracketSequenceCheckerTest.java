package ru.mail.polis.open.task3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CorrectBracketSequenceCheckerTest {

    private static final String simpleCorrect = "()";
    private static final String simpleWrong = "())";
    private static final String closeWrong = "]";
    private static final String empty = "";
    private static final String symbolsWrong = "()1[]";
    private static final String nearCorrect = "()[]{}";
    private static final String spaceWrong = " ";
    private static final String spaceBetweenWrong = "() ()";
    private static final String innerCorrect = "(())";
    private static final String complicatedInnerCorrect = "({}[])";
    private static final String moreComplicatedInnerCorrect = "({}[])({}[])";
    private static final String hugeWrong = "({}[])({}[])({}[])({}[])({}[])({}[])" +
        "({}[])({}[])({}[])({}[])({}[])({}[])({}[])({}[])({}[])" +
        "({}[])({}[])({}[])({}[])({}[])({}[])({}[])({}[])({}[])";

    @BeforeEach
    void refresh() {
        CorrectBracketSequenceChecker.refreshStatistics();
    }

    @Test
    void checkSequence() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(simpleCorrect));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(simpleWrong));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(closeWrong));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(empty));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(symbolsWrong));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(nearCorrect));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(spaceWrong));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(spaceBetweenWrong));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(innerCorrect));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(complicatedInnerCorrect));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(moreComplicatedInnerCorrect));
        assertThrows(
            IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence(hugeWrong)
        );
    }

    @Test
    void getSuccessChecksCount() {
        assertEquals(0, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence(simpleCorrect);
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence(simpleWrong);
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence(closeWrong);
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence(empty);
        assertEquals(2, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void getFailChecksCount() {
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence(simpleCorrect);
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence(simpleWrong);
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence(closeWrong);
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence(empty);
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void getLastSuccessSequence() {
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
        CorrectBracketSequenceChecker.checkSequence(simpleCorrect);
        assertEquals(simpleCorrect, CorrectBracketSequenceChecker.getLastSuccessSequence());
        assertFalse(CorrectBracketSequenceChecker.checkSequence(simpleWrong));
        assertEquals(simpleCorrect, CorrectBracketSequenceChecker.getLastSuccessSequence());
        assertFalse(CorrectBracketSequenceChecker.checkSequence(closeWrong));
        assertEquals(simpleCorrect, CorrectBracketSequenceChecker.getLastSuccessSequence());
        assertTrue(CorrectBracketSequenceChecker.checkSequence(empty));
        assertEquals(empty, CorrectBracketSequenceChecker.getLastSuccessSequence());
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
        CorrectBracketSequenceChecker.checkSequence(simpleCorrect);
        CorrectBracketSequenceChecker.checkSequence(closeWrong);
        CorrectBracketSequenceChecker.checkSequence(complicatedInnerCorrect);
        CorrectBracketSequenceChecker.checkSequence(moreComplicatedInnerCorrect);
        assertEquals(3, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());
        assertEquals(moreComplicatedInnerCorrect, CorrectBracketSequenceChecker.getLastSuccessSequence());
        CorrectBracketSequenceChecker.refreshStatistics();
        assertEquals(0, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}