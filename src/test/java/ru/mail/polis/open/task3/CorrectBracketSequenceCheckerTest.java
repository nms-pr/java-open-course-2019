package ru.mail.polis.open.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CorrectBracketSequenceCheckerTest {

    @BeforeEach
    void reset(){
        CorrectBracketSequenceChecker.reset();
    }

    @Test
    void checkSequence() {
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence(")("));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("()"));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("({}[])"));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("(){}}[]"));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("({[({[}{))("));
    }

    @Test
    void reverseBracket() {
        Assertions.assertEquals('(', CorrectBracketSequenceChecker.reverseBracket(')'));
        Assertions.assertEquals('{', CorrectBracketSequenceChecker.reverseBracket('}'));
        Assertions.assertEquals('[', CorrectBracketSequenceChecker.reverseBracket(']'));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.reverseBracket('('));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.reverseBracket('{'));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.reverseBracket('['));

    }

    @Test
    void getSuccessChecksCount() {
        CorrectBracketSequenceChecker.checkSequence("()");
        CorrectBracketSequenceChecker.checkSequence("({}[])");
        CorrectBracketSequenceChecker.checkSequence("(){}}[]");
        CorrectBracketSequenceChecker.checkSequence("({[({[}{))(");
        Assertions.assertEquals(2, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void getFailChecksCount() {
        CorrectBracketSequenceChecker.checkSequence("()");
        CorrectBracketSequenceChecker.checkSequence("({}[])");
        CorrectBracketSequenceChecker.checkSequence("(){}}[]");
        CorrectBracketSequenceChecker.checkSequence("({[({[}{))(");
        Assertions.assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void getLastSuccessSequence() {
        CorrectBracketSequenceChecker.checkSequence(")(");
        CorrectBracketSequenceChecker.checkSequence("()");
        CorrectBracketSequenceChecker.checkSequence("({}[])");
        CorrectBracketSequenceChecker.checkSequence("(){}}[]");
        CorrectBracketSequenceChecker.checkSequence("({[({[}{))(");
        Assertions.assertEquals("({}[])", CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}
