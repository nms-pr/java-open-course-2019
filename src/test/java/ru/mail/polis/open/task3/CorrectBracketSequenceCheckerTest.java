package ru.mail.polis.open.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class CorrectBracketSequenceCheckerTest {

    @Test
    void checkCorrectSequence() {

        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence(""));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("()"));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("[]"));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("{}"));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("{()}"));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("[](){}"));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("([{}])"));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("[]({})"));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("([]){}"));

    }

    @Test
    void checkWrongSequence() {

        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("("));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("{"));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("["));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence(")"));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("}"));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("]"));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("([{"));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence(")]}"));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("([]"));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("{[}]"));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("[()]{"));

    }

    @Test
    void inputStringLengthMustBeLessThan100() {

        try {
            CorrectBracketSequenceChecker.checkSequence("({[}");
        } catch (Throwable e) {
            fail(e);
        }

        String largeTestString = "((((((((((" + "{{{{{{{{{{" + "[[[[[[[[[[" + "))))))))))" + "}}}}}}}}}}"
                + "))))(({{}[" + "{{}{}{}}}}" + "[]][()))((" + "{{}][({]))" + "[]][][[]][" + "[";
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence(largeTestString));


    }

    @Test
    void testNullableString() {

        Assertions.assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence(null));

    }

    @Test
    void testStringWithIncorrectSymbol() {

        Assertions.assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence("()[]7{}"));

        Assertions.assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence("f{}"));

        Assertions.assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence("[/"));
    }

    @Test
    void successChecksCountTest() {

        CorrectBracketSequenceChecker.checkSequence("()");
        Assertions.assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("(){}[]");
        Assertions.assertEquals(3, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("({})");
        Assertions.assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("{[]");
        Assertions.assertEquals(0, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("[]}");
        Assertions.assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void failChecksCountTest() {

        CorrectBracketSequenceChecker.checkSequence("()");
        Assertions.assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("(){}[]");
        Assertions.assertEquals(3, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("({})");
        Assertions.assertEquals(3, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("{[]");
        Assertions.assertEquals(3, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("[]}");
        Assertions.assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());

    }

    @Test
    void lastSuccessSequenceTest() {

        CorrectBracketSequenceChecker.checkSequence("()");
        Assertions.assertEquals("()", CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("(){}[]");
        Assertions.assertEquals("(){}[]", CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("({})");
        Assertions.assertEquals("({})", CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("{[]");
        Assertions.assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("[]}");
        Assertions.assertEquals("[]", CorrectBracketSequenceChecker.getLastSuccessSequence());
    }

}