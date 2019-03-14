package ru.mail.polis.open.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.CharBuffer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CorrectBracketSequenceCheckerTest {

    @BeforeEach
    void clearStatisticsInChecker() {
        CorrectBracketSequenceChecker.clearAll();
    }

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
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([{]})invalid"));
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

    @Test
    void getSuccessCheckCount_CountingCorrectly() {
        assertEquals(0, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence(null);
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("");
        assertEquals(2, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("{}");
        assertEquals(3, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("{}[]");
        CorrectBracketSequenceChecker.checkSequence("[]");
        assertEquals(5, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("{");
        assertEquals(5, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void getFailCheckCount_CountingCorrectly() {
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence(null);
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("");
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("{}");
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("[");
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("{}{");
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("{[}]");
        assertEquals(3, CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void getLastSuccessSequence_If_Never_Checked_Then_Null() {
        assertNull(null, CorrectBracketSequenceChecker.getLastSuccessSequence());
    }


    @Test
    void getLastSuccessSequence_Works_Correctly() {

        final String testString1 = "{}[]()";
        CorrectBracketSequenceChecker.checkSequence(testString1);
        assertEquals(testString1, CorrectBracketSequenceChecker.getLastSuccessSequence());


        final String testString2 = "{{}[]()}";
        CorrectBracketSequenceChecker.checkSequence(testString2);
        assertEquals(testString2, CorrectBracketSequenceChecker.getLastSuccessSequence());
    }


    @Test
    void getLastSuccessSequence_Returns_Correct_When_Previous_Was_Incorrect() {

        final String invalidString = "[";

        CorrectBracketSequenceChecker.checkSequence(invalidString);
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());

        final String testString = "{}[]()";
        CorrectBracketSequenceChecker.checkSequence(testString);
        assertEquals(testString, CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence(invalidString);
        assertEquals(testString, CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}