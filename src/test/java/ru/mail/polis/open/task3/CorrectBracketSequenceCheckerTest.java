package ru.mail.polis.open.task3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CorrectBracketSequenceCheckerTest {

    @BeforeEach
    void setDefault() {
        CorrectBracketSequenceChecker.setDefault();
    }

    @Test
    void notBracketsSequence() {
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("12AB"));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("{}[]}[123"));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("{}[]()123"));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("{}[|]()"));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            sb.append("(");
        }
        String sf = sb.toString();
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence(sf));

        assertTrue(CorrectBracketSequenceChecker.checkSequence(""));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(null));
    }

    @Test
    void emptyString() {
        String sequance = "";

        assertTrue(CorrectBracketSequenceChecker.checkSequence(sequance));
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());
        assertEquals("", CorrectBracketSequenceChecker.getLastSuccessSequence());
    }

    @Test
    void successCheck() {
        String sequance = "({}[()]{[]})";

        assertTrue(CorrectBracketSequenceChecker.checkSequence(sequance));
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());
        assertEquals(sequance, CorrectBracketSequenceChecker.getLastSuccessSequence());

        assertTrue(CorrectBracketSequenceChecker.checkSequence(sequance));
        assertEquals(2, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());
        assertEquals("({}[()]{[]})", CorrectBracketSequenceChecker.getLastSuccessSequence());
    }

    @Test
    void failCheck() {
        String sequance = "({}][])";

        assertFalse(CorrectBracketSequenceChecker.checkSequence(sequance));
        assertEquals(0, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());
        assertEquals(null, CorrectBracketSequenceChecker.getLastSuccessSequence());

        assertFalse(CorrectBracketSequenceChecker.checkSequence(sequance));
        assertEquals(0, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());
        assertEquals(null, CorrectBracketSequenceChecker.getLastSuccessSequence());
    }

    @Test
    void successAndFailCheck() {
        String successSequance = "({}[()]{[]})";
        String failsequance = "({}][])";

        assertTrue(CorrectBracketSequenceChecker.checkSequence(successSequance));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(failsequance));

        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());
        assertEquals("({}[()]{[]})", CorrectBracketSequenceChecker.getLastSuccessSequence());
    }

}