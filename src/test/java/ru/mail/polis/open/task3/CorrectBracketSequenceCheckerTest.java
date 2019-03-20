package ru.mail.polis.open.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CorrectBracketSequenceCheckerTest {

    @Test
    void emptyStringTest() {
        assertEquals(true, CorrectBracketSequenceChecker.checkSequence(("")));
    }

    @Test
    void isStringMoreThen100() {
        String m101 = new String(new char[101]);
        assertEquals(false, CorrectBracketSequenceChecker.checkSequence(m101));
    }

    @Test
    void correctSequence() {
        assertEquals(true, CorrectBracketSequenceChecker.checkSequence("{([])}"));
        assertEquals(true, CorrectBracketSequenceChecker.checkSequence("{{}}[[]]"));
        assertEquals(true, CorrectBracketSequenceChecker.checkSequence("{}"));
    }

    @Test
    void incorrectSequence() {
        assertEquals(false, CorrectBracketSequenceChecker.checkSequence("(({{}"));
        assertEquals(false, CorrectBracketSequenceChecker.checkSequence(")("));
    }

    @Test
    void incorrectSymbolsInString() {
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("{{ff}}")
        );
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("f{}")
        );
    }

    @Test
    void correctSequenceWithSpaces() {
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("{{  }}")
        );
    }

    @Test
    void checkingStateCounters() {
        CorrectBracketSequenceChecker.checkSequence("{{}}");
        Assertions.assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("{{{");
        Assertions.assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void isReturnLastSuccessSequence() {
        CorrectBracketSequenceChecker.checkSequence("{{}}");
        Assertions.assertEquals("{{}}", CorrectBracketSequenceChecker.getLastSuccessSequence());
    }

    @Test
    void isNoCorrectSequenceAndReturnNull() {
        CorrectBracketSequenceChecker.checkSequence("{{{");
        assertEquals(null, CorrectBracketSequenceChecker.getLastSuccessSequence());
    }

}
