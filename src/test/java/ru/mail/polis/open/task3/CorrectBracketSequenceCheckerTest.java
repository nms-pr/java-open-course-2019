package ru.mail.polis.open.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CorrectBracketSequenceCheckerTest {

    @Test
    void checkRigthSequence() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence("()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("(())"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{[()]}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({})[]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("(){}[]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(""));
    }

    @Test
    void checkWrongSequence() {
        assertFalse(CorrectBracketSequenceChecker.checkSequence("{"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("("));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(")"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("["));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(({"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("}})"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("()}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(()){"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({[]})}"));
    }

    @Test
    void checkLongSequence() {
        assertThrows(
                IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("()"
                        + "()()()()()()()()()()()()()"
                        + "()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()"
                        + "()()()()()()()()"
                        + "()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()("
                        + ")()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()"
                        + "()()()()()()()()()()()()")
        );
    }

    @Test
    void checkWrongSymbols() {
        assertThrows(
                IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("000")
        );
    }

    @Test
    void getSuccessChecksCount() {
        CorrectBracketSequenceChecker.checkSequence("()()");
        CorrectBracketSequenceChecker.checkSequence("({})");
        CorrectBracketSequenceChecker.checkSequence("[][][]");
        CorrectBracketSequenceChecker.checkSequence("((((");
        assertEquals(3, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void getFailChecksCount() {
        CorrectBracketSequenceChecker.checkSequence("()()");
        CorrectBracketSequenceChecker.checkSequence("[][][");
        CorrectBracketSequenceChecker.checkSequence("[])");
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());


    }

    @Test
    void getLastSuccessSequence() {
        CorrectBracketSequenceChecker.checkSequence("))))))))");
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence(), "message");
        CorrectBracketSequenceChecker.checkSequence("()()");
        assertEquals("()()", CorrectBracketSequenceChecker.getLastSuccessSequence());

    }

    @BeforeEach
    void resetCounters() {
        CorrectBracketSequenceChecker.reset();
    }
}