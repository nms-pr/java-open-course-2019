package ru.mail.polis.open.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CorrectBracketSequenceCheckerTest {

    @BeforeEach
    void clearCounters() {
        new CorrectBracketSequenceChecker();
    }

    private static final String EMPTY = "";
    private static final String NULL = null;
    private static final String FIRST_CORRECT = "()(){}{[[]]}";
    private static final String FIRST_INCORRECT = "(({{})}]";
    private static final String SECOND_CORRECT = "{{{()}}}[()]{[]}";
    private static final String SECOND_INCORRECT = "{{}{}}(()))){{}[";
    private static final String THIRD_CORRECT = "{{(([]))}}{()}[()]";
    private static final String THIRD_INCORRECT = "{}{{{()[]()([[)((({}{[][";
    private static final String INCORRECT_SYMBOL_FIRST = "{{{((323))}}}";
    private static final String INCORRECT_SYMBOL_SECOND = "123{34}(2)";
    private static final String INCORRECT_MORE_100_SYMBOLS = "()([][][])()()([][][][])()()()()({}{{}}"
        + ")()()()()()[][[]]()(){{{[]}}}()()[][][][][]"
        + "{}{}{}{}[][]{}{{{{}}}}()()()()()()()()";
    private static final String WITH_SPACE = "((( ))){}{ } []";

    @Test
    void checkSequence() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(EMPTY));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(FIRST_CORRECT));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(SECOND_CORRECT));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(THIRD_CORRECT));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(FIRST_INCORRECT));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(SECOND_INCORRECT));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(THIRD_INCORRECT));
        assertThrows(
            IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence(INCORRECT_SYMBOL_FIRST)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence(INCORRECT_SYMBOL_SECOND)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence(INCORRECT_MORE_100_SYMBOLS)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence(WITH_SPACE)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence(NULL)
        );
    }

    @Test
    void getSuccessCheckCount() {
        CorrectBracketSequenceChecker.checkSequence(EMPTY);
        CorrectBracketSequenceChecker.checkSequence(FIRST_CORRECT);
        CorrectBracketSequenceChecker.checkSequence(SECOND_CORRECT);
        CorrectBracketSequenceChecker.checkSequence(FIRST_INCORRECT);
        assertEquals(3, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void getFailedCheckCount() {
        CorrectBracketSequenceChecker.checkSequence(SECOND_CORRECT);
        CorrectBracketSequenceChecker.checkSequence(FIRST_INCORRECT);
        CorrectBracketSequenceChecker.checkSequence(SECOND_INCORRECT);
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void getLastSuccessSequence() {
        CorrectBracketSequenceChecker.checkSequence(EMPTY);
        CorrectBracketSequenceChecker.checkSequence(FIRST_CORRECT);
        CorrectBracketSequenceChecker.checkSequence(SECOND_CORRECT);
        CorrectBracketSequenceChecker.checkSequence(FIRST_INCORRECT);
        assertEquals(SECOND_CORRECT, CorrectBracketSequenceChecker.getLastSuccessSequence());
        new CorrectBracketSequenceChecker();
        CorrectBracketSequenceChecker.checkSequence(THIRD_INCORRECT);
        CorrectBracketSequenceChecker.checkSequence(FIRST_INCORRECT);
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}
