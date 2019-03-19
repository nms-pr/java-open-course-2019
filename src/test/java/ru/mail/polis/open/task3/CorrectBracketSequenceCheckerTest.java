package ru.mail.polis.open.task3;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class CorrectBracketSequenceCheckerTest {


    @Test
    void checkSequence() {
        assertFalse(CorrectBracketSequenceChecker.checkSequence(")("));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({}[])"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(){}}[]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({[({[}{))("));
    }

    @Test
    void reverseBracket() {
        assertEquals('(', CorrectBracketSequenceChecker.reverseBracket(')'));
        assertEquals('{', CorrectBracketSequenceChecker.reverseBracket('}'));
        assertEquals('[', CorrectBracketSequenceChecker.reverseBracket(']'));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.reverseBracket('('));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.reverseBracket('{'));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.reverseBracket('['));

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
