package ru.mail.polis.open.task3.solution;

import ru.mail.polis.open.task3.CorrectBracketSequenceChecker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class CorrectBracketSequenceCheckerTest {

    @Test
    void nullInput() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(null));
    }

    @Test
    void longInputString() {
        StringBuilder inputString = new StringBuilder(100);
        for (int i = 0; i < 101; i++) {
            inputString.append('(');
        }
        assertThrows(IllegalArgumentException.class, () -> {
            CorrectBracketSequenceChecker.checkSequence(inputString.toString());
        });
    }

    @Test
    void emptyInput() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(""));
    }

    @Test
    void trueInput() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence("()[{}]"));
    }

    @Test
    void falseBracketsInput() {
        assertFalse(CorrectBracketSequenceChecker.checkSequence("()[)]"));
    }

    @Test
    void falseSymbolsInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            CorrectBracketSequenceChecker.checkSequence("(f)[)]");
        });
    }

    @Test
    void checkSuccessChecksCount() {
        CorrectBracketSequenceChecker.resetSuccessChecksCount();
        CorrectBracketSequenceChecker.checkSequence("()[{}]");
        CorrectBracketSequenceChecker.checkSequence("()[)]");
        CorrectBracketSequenceChecker.checkSequence("()[)))]");
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void checkFailChecksCount() {
        CorrectBracketSequenceChecker.resetFailChecksCount();
        CorrectBracketSequenceChecker.checkSequence("()[{}]");
        CorrectBracketSequenceChecker.checkSequence("()[{}]{}");
        CorrectBracketSequenceChecker.checkSequence("()[)]");
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void checkLastSuccessSequence() {
        CorrectBracketSequenceChecker.resetLastSuccessSequence();
        String lastSuccessSequence = "()[{}]";
        CorrectBracketSequenceChecker.checkSequence(lastSuccessSequence);
        CorrectBracketSequenceChecker.checkSequence("()[)]");
        assertEquals(lastSuccessSequence, CorrectBracketSequenceChecker.getLastSuccessSequence());
    }

    @Test
    void checkNullLastSuccessSequence() {
        CorrectBracketSequenceChecker.resetLastSuccessSequence();
        CorrectBracketSequenceChecker.checkSequence("()[)]");
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}
