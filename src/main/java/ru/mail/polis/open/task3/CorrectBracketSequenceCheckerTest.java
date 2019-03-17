package ru.mail.polis.open.task3;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CorrectBracketSequenceCheckerTest {

    @Test
    void emptyStringTest() {
        Assertions.assertEquals(true, CorrectBracketSequenceChecker.checkSequence(("")));
    }

    @Test
    void isStringMoreThen100() {
        String m101 = new String(new char[101]);
        Assertions.assertEquals(false, CorrectBracketSequenceChecker.checkSequence(m101) );
    }

    @Test
    void correctSequence() {
        String test1 = "{([])}";
        Assertions.assertEquals(true, CorrectBracketSequenceChecker.checkSequence(test1));
    }

    @Test
    void incorrectSequence() {
        String test = new String("(({{}");
        Assertions.assertEquals(false, CorrectBracketSequenceChecker.checkSequence(test));
    }

    @Test
    void incorrectSymbolsInString() {
        String test = new String("{{ff}}");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> CorrectBracketSequenceChecker.checkSequence(test));
    }
}
