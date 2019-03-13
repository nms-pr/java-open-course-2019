package ru.mail.polis.open.task3;

import org.junit.jupiter.api.Test;

import java.nio.CharBuffer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CorrectBracketSequenceCheckerTest {

    @Test
    void checkSequence_HasNonStringCharacter() {

        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("1"));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("{1}{2}"));
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("[1}"));
    }

    @Test
    void checkSequence_StringHasLessThanHundredCharacters() {

        assertDoesNotThrow(() -> CorrectBracketSequenceChecker.checkSequence("{}"));
        assertDoesNotThrow(() -> CorrectBracketSequenceChecker.checkSequence("{}[]"));
        assertDoesNotThrow(() -> CorrectBracketSequenceChecker.checkSequence("{}[]"));

        final String testString1 = CharBuffer.allocate(99).toString().replace('\0', '{');
        assertDoesNotThrow(() -> CorrectBracketSequenceChecker.checkSequence(testString1));

        final String testString2 = CharBuffer.allocate(100).toString().replace('\0', '{');;
        assertDoesNotThrow(() -> CorrectBracketSequenceChecker.checkSequence(testString2));
    }

    @Test
    void checkSequence_StringHasMoreThanHundredCharacters() {

        final String testString = CharBuffer.allocate(101).toString();

        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence(testString));
    }

    @Test
    void checkSequence_InvalidString() {
        assertFalse(CorrectBracketSequenceChecker.checkSequence("{"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("{[}]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([{]})"));
    }

    @Test
    void checkSequence_ValidString() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(""));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(null));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[]()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("([])"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("([]){}[([])]{}"));
    }
}