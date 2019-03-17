package ru.mail.polis.open.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class CorrectBracketSequenceCheckerTest {

    @BeforeEach
    private void clearCache() {
        CorrectBracketSequenceChecker.clear();
    }

    @Test
    void testIllegalInputs() {
        try {
            CorrectBracketSequenceChecker.checkSequence("()");
        } catch (Throwable e) {
            fail(e);
        }
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence(null));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("{("
                + "[Letters])}"));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("{[()]{}}("
                + "())[(){}](){[()]{}}(())[(){}](){[()]{}}(())[(){}](){[()]{}}(())[(){}](){[()]{}}(())[(){}](){[()"
                + "]{}}(())[(){}]()"));
    }

    @Test
    void testLegalInputs() {
        assertThrows(IllegalStateException.class, CorrectBracketSequenceChecker::getLastSuccessSequence);
        assertTrue(CorrectBracketSequenceChecker.checkSequence("(){}[[()]]"));
        assertEquals("(){}[[()]]", CorrectBracketSequenceChecker.getLastSuccessSequence());
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(({]]]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("))"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(")"));
        assertEquals(3, CorrectBracketSequenceChecker.getFailChecksCount());
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void testClear() {
        CorrectBracketSequenceChecker.checkSequence("()){}{[[}");
        CorrectBracketSequenceChecker.checkSequence("()()(){{[]{{}}()}}");
        CorrectBracketSequenceChecker.clear();
        assertEquals(0, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());
        assertThrows(IllegalStateException.class, CorrectBracketSequenceChecker::getLastSuccessSequence);
    }
}
