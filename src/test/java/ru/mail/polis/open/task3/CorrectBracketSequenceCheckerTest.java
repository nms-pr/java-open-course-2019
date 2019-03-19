package ru.mail.polis.open.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CorrectBracketSequenceCheckerTest {
    @BeforeEach
    void nullingVariables() {
        CorrectBracketSequenceChecker.numberOfSuccessfulChecks = 0;
        CorrectBracketSequenceChecker.numberOfUnsuccessfulChecks = 0;
        CorrectBracketSequenceChecker.lastSuccessSequence = "";
    }

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
        String test1 = "{([])}";
        assertEquals(true, CorrectBracketSequenceChecker.checkSequence(test1));
    }

    @Test
    void incorrectSequence() {
        String test = new String("(({{}");
        assertEquals(false, CorrectBracketSequenceChecker.checkSequence(test));
    }

    @Test
    void incorrectSymbolsInString() {
        String test = new String("{{ff}}");
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence(test)
        );
    }

    @Test
    void correctSequenceWithSpaces() {
        String test = new String("{{  }}");
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence(test)
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

}
