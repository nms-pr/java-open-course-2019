package ru.mail.polis.open.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CorrectBracketSequenceCheckerTest {

    private static final String EMPTY = "";
    private static final String NIL = null;
    private static final String MORE_HUNDRED = "(((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((("
            + ")))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))";

    private static final String COR1 = "{}[]()";
    private static final String COR2 = "[({}())]";
    private static final String COR3 = "[({}[]())]";
    private static final String COR4 = "[]({[]})";
    private static final String COR5 = "({(([[]]))})";

    private static final String INCOR1 = "{}}";
    private static final String INCOR2 = "[";
    private static final String INCOR3 = "[({])}";
    private static final String INCOR4 = "[({])(}[}])";
    private static final String INCOR5 = "[({])}{)(]";

    @BeforeEach
    void resetCounters() {
        CorrectBracketSequenceChecker.reset();
    }

    @Test
    void checkSequence() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(EMPTY));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(COR1));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(COR2));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(COR3));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(COR4));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(COR5));

        assertFalse(CorrectBracketSequenceChecker.checkSequence(INCOR1));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(INCOR2));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(INCOR3));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(INCOR4));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(INCOR5));

        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence(NIL));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence(MORE_HUNDRED));
    }

    @Test
    void getSuccessChecksCount() {
        CorrectBracketSequenceChecker.checkSequence(EMPTY);
        CorrectBracketSequenceChecker.checkSequence(COR1);
        CorrectBracketSequenceChecker.checkSequence(COR2);
        CorrectBracketSequenceChecker.checkSequence(COR3);
        CorrectBracketSequenceChecker.checkSequence(COR4);
        CorrectBracketSequenceChecker.checkSequence(COR5);
        assertEquals(6, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void getFailChecksCount() {
        CorrectBracketSequenceChecker.checkSequence(INCOR1);
        CorrectBracketSequenceChecker.checkSequence(INCOR2);

        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void getLastSuccessSequence() {
        CorrectBracketSequenceChecker.checkSequence(COR1);
        CorrectBracketSequenceChecker.checkSequence(COR3);

        assertEquals(COR3, CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}
