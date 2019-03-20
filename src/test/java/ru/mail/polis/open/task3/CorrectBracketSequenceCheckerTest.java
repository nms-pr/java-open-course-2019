package ru.mail.polis.open.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CorrectBracketSequenceCheckerTest {

    @BeforeEach
    void clear() {
        CorrectBracketSequenceChecker.setFailChecksCount(0);
        CorrectBracketSequenceChecker.setSuccessChecksCount(0);
    }

    @Test
    void checkSequence() {
        assertFalse(CorrectBracketSequenceChecker.checkSequence(")("));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({}[])"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(){}}[]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({[({[}{))("));
    }

    @Test
    void oppositeBracket() {
        assertEquals('(', CorrectBracketSequenceChecker.oppositeBracket(')'));
        assertEquals('{', CorrectBracketSequenceChecker.oppositeBracket('}'));
        assertEquals('[', CorrectBracketSequenceChecker.oppositeBracket(']'));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.oppositeBracket('('));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.oppositeBracket('{'));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.oppositeBracket('['));

    }

    @Test
    void getSuccessChecksCount() {
        CorrectBracketSequenceChecker.checkSequence("()");
        CorrectBracketSequenceChecker.checkSequence("({}[])");
        CorrectBracketSequenceChecker.checkSequence("(){}}[]");
        CorrectBracketSequenceChecker.checkSequence("({[({[}{))(");
        assertEquals(2, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void getFailChecksCount() {
        CorrectBracketSequenceChecker.checkSequence("()");
        CorrectBracketSequenceChecker.checkSequence("({}[])");
        CorrectBracketSequenceChecker.checkSequence("(){}}[]");
        CorrectBracketSequenceChecker.checkSequence("({[({[}{))(");
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void getLastSuccessSequence() {
        CorrectBracketSequenceChecker.checkSequence(")(");
        CorrectBracketSequenceChecker.checkSequence("()");
        CorrectBracketSequenceChecker.checkSequence("({}[])");
        CorrectBracketSequenceChecker.checkSequence("(){}}[]");
        CorrectBracketSequenceChecker.checkSequence("({[({[}{))(");
        assertEquals("({}[])", CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}