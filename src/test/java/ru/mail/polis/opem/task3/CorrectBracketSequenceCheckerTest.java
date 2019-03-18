package ru.mail.polis.opem.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task3.CorrectBracketSequenceChecker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CorrectBracketSequenceCheckerTest {

    @BeforeEach
    void resetCountersOfClassCorrectBracketSequenceChecker() {
        CorrectBracketSequenceChecker.reset();
    }

    @Test
    void testReturnTrueOfMethodsCheckSequence() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(""));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("()()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("(){}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{}[]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("()[]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{}[]()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{}((())){[[[]]]}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{}[((({()})))]"));
    }

    @Test
    void testReturnFalseOfMethodsCheckSequence() {
        assertFalse(CorrectBracketSequenceChecker.checkSequence("("));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("{"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("["));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence(")"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("))"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({})["));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({))[]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("[({]})"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("[[[{{(]]]}})"));
    }

    @Test
    public void testException() {
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("[]()f[[]]"));
        assertThrows(IllegalArgumentException.class, () -> assertFalse(CorrectBracketSequenceChecker.checkSequence(
                "[][][][][][][][][][]"
                        + "[][][][][][][][][][]"
                        + "[][][][][][][][][][]"
                        + "[][][][][][][][][][]"
                        + "[][][][][][][][][][][")));

        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence(null));
    }

    @Test
    void testCorrectSuccessChecksCount() {
        assertEquals(0, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("[][](){{}}");
        CorrectBracketSequenceChecker.checkSequence("[({})]");
        assertEquals(2, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("[({})]");
        assertEquals(3, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("[({})][[[()]]]");
        CorrectBracketSequenceChecker.checkSequence("[({})]()");
        CorrectBracketSequenceChecker.checkSequence("[({})]({})");
        CorrectBracketSequenceChecker.checkSequence("[({})]["); // fail
        assertEquals(6, CorrectBracketSequenceChecker.getSuccessChecksCount());

    }

    @Test
    void testCorrectFailChecksCount() {
        assertEquals(0, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("([]");
        CorrectBracketSequenceChecker.checkSequence("([][))");
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("}");
        CorrectBracketSequenceChecker.checkSequence("[([)]]");
        CorrectBracketSequenceChecker.checkSequence("([]{}");
        assertEquals(5, CorrectBracketSequenceChecker.getFailChecksCount());
    }


    @Test
    void lastSuccessSequenceTest() {

        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("[]");
        assertEquals("[]", CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("()()(){}[]{{{[][]}}}");
        CorrectBracketSequenceChecker.checkSequence("({[{]})");//fail
        assertEquals("()()(){}[]{{{[][]}}}", CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("({[]})");
        CorrectBracketSequenceChecker.checkSequence("({[[]])((");//fail
        assertEquals("({[]})", CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("({[]})[][]");
        CorrectBracketSequenceChecker.checkSequence("({[]}){}{}[[[[]]]][][][][]");
        assertEquals("({[]}){}{}[[[[]]]][][][][]", CorrectBracketSequenceChecker.getLastSuccessSequence());

    }

}
