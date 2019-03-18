package ru.mail.polis.open.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CorrectBracketSequenceCheckerTest {

    private static final String EMPTY = "";
    private static final String NULL = null;
    private static final String MORE_HUNDRED = "((((((((((((((((((((((((((((((((((((((((((((((((((("
            + ")))))))))))))))))))))))))))))))))))))))))))))))))))";

    private static final String CORRECT_ONE = "{}[]()";
    private static final String CORRECT_TWO = "[({}[]())]";
    private static final String CORRECT_THREE = "[({}[]())]";

    private static final String INCORRECT_ONE = "())";
    private static final String INCORRECT_TWO = "(";
    private static final String INCORRECT_THREE = "[(])";

    @BeforeEach
    void resetCounters() {
        CorrectBracketSequenceChecker.reset();
    }

    @Test
    void checkSequence() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(EMPTY));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(CORRECT_ONE));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(CORRECT_TWO));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(CORRECT_THREE));

        assertFalse(CorrectBracketSequenceChecker.checkSequence(INCORRECT_ONE));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(INCORRECT_TWO));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(INCORRECT_THREE));

        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence(NULL));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence(MORE_HUNDRED));
    }

    @Test
    void getSuccessChecksCount() {
        CorrectBracketSequenceChecker.checkSequence(EMPTY);
        CorrectBracketSequenceChecker.checkSequence(CORRECT_ONE);
        CorrectBracketSequenceChecker.checkSequence(CORRECT_TWO);
        CorrectBracketSequenceChecker.checkSequence(CORRECT_THREE);

        assertEquals(4, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void getFailChecksCount() {
        CorrectBracketSequenceChecker.checkSequence(INCORRECT_ONE);
        CorrectBracketSequenceChecker.checkSequence(INCORRECT_TWO);

        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void getLastSuccessSequence() {
        CorrectBracketSequenceChecker.checkSequence(CORRECT_ONE);
        CorrectBracketSequenceChecker.checkSequence(CORRECT_TWO);

        assertEquals(CORRECT_TWO, CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}