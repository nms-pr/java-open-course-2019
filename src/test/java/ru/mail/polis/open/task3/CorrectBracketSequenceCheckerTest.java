package ru.mail.polis.open.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CorrectBracketSequenceCheckerTest {

    private static final String EMPTY = "";
    private static final String NULLSTRING = null;
    private static final String LONGSTRING = "[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[["
            + "]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]";

    private static final String FIRST_TRUE = "{{}}";
    private static final String SECOND_TRUE = "[(){}]";
    private static final String THIRD_TRUE = "{{[](){[]}}}";

    private static final String FIRST_FALSE = "()]";
    private static final String SECOND_FALSE = "]";
    private static final String THIRD_FALSE = "[(]}";

    @BeforeEach
    void resetCount() {
        CorrectBracketSequenceChecker.reset();
    }

    @Test
    void checkSequence() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(EMPTY));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(FIRST_TRUE));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(SECOND_TRUE));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(THIRD_TRUE));

        assertFalse(CorrectBracketSequenceChecker.checkSequence(FIRST_FALSE));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(SECOND_FALSE));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(THIRD_FALSE));

        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence(NULLSTRING));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence(LONGSTRING));
    }

    @Test
    void getSuccessChecksCount() {
        CorrectBracketSequenceChecker.checkSequence(EMPTY);
        CorrectBracketSequenceChecker.checkSequence(FIRST_TRUE);
        CorrectBracketSequenceChecker.checkSequence(SECOND_TRUE);
        CorrectBracketSequenceChecker.checkSequence(THIRD_TRUE);

        assertEquals(4, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void getFailChecksCount() {
        CorrectBracketSequenceChecker.checkSequence(FIRST_FALSE);
        CorrectBracketSequenceChecker.checkSequence(SECOND_FALSE);
        CorrectBracketSequenceChecker.checkSequence(THIRD_FALSE);

        assertEquals(3, CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void getLastSuccessLine() {
        CorrectBracketSequenceChecker.checkSequence(FIRST_TRUE);
        CorrectBracketSequenceChecker.checkSequence(SECOND_TRUE);

        assertEquals(SECOND_TRUE, CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}