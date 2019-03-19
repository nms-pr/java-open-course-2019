package ru.mail.polis.open.task3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mail.polis.open.task3.CorrectBracketSequenceChecker.checkSequence;

class TestCorrectBracketSequenceChecker {

    @AfterEach
    void resetCounters() {
        CorrectBracketSequenceChecker.reset();
    }

    @Test
    void testReturnTrueCheckSequence() {
        assertTrue(checkSequence(""));
        assertTrue(checkSequence("()"));
        assertTrue(checkSequence("[]"));
        assertTrue(checkSequence("{}"));
        assertTrue(checkSequence("(())"));
        assertTrue(checkSequence("(){}[]()"));
    }

    @Test
    void testReturnFalseCheckSequence() {
        assertFalse(checkSequence("(("));
        assertFalse(checkSequence("}{"));
        assertFalse(checkSequence("[[]"));
        assertFalse(checkSequence("())"));
        assertFalse(checkSequence("()[]{}}"));
        assertFalse(checkSequence(")()[]{}}"));
    }

    @Test
    void testThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                CorrectBracketSequenceChecker.checkSequence("()()()й"));
        assertThrows(IllegalArgumentException.class, () ->
            assertFalse(CorrectBracketSequenceChecker.checkSequence("(){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}" +
                    "(){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[][](){}[](){}[](){}[]" +
                    "(){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[](){}[]()" +
                    "{}[](){}[]{}[](){}[]{}[](){}[]{}[](){}[]{}[](){}[]{}[](){}[]{}[](){}[]{}[](){}[]")));
        assertThrows(IllegalArgumentException.class, () ->
                CorrectBracketSequenceChecker.checkSequence(null));
    }

    @Test
    void testSuccessCheckCount() {
        assertEquals(0, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("(()){{}}[{}]");
        CorrectBracketSequenceChecker.checkSequence("([{}])");
        CorrectBracketSequenceChecker.checkSequence("()()()()");
        assertEquals(3, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("((({{[]}})))");
        assertEquals(4, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void testFailCheckCount() {
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence("))(");
        CorrectBracketSequenceChecker.checkSequence("}}}}");
        CorrectBracketSequenceChecker.checkSequence("()()[}");
        assertEquals(3, CorrectBracketSequenceChecker.getFailChecksCount());
        assertThrows(IllegalArgumentException.class, () ->
                CorrectBracketSequenceChecker.checkSequence("fdwfswefw"));
        assertThrows(IllegalArgumentException.class, () ->
                CorrectBracketSequenceChecker.checkSequence("()q(){}"));
        assertEquals(5, CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void testGetLastSuccessSequence() {
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
        CorrectBracketSequenceChecker.checkSequence("}(}()");
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
        CorrectBracketSequenceChecker.checkSequence("[][][]");
        assertEquals("[][][]", CorrectBracketSequenceChecker.getLastSuccessSequence());
        assertThrows(IllegalArgumentException.class, () ->
                CorrectBracketSequenceChecker.checkSequence("dsfdgre"));
        assertEquals("[][][]", CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}
