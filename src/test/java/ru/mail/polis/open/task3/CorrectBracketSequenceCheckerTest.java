package ru.mail.polis.open.task3;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CorrectBracketSequenceCheckerTest {

    @Test
    public void testSequenceTrue() {
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence(""));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("()"));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("[]"));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("{}"));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("{}()[]"));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("({}()[])"));
        Assertions.assertTrue(CorrectBracketSequenceChecker.checkSequence("({}()[])[{()()}]"));

    }

    @Test
    public void testSequenceFalse() {
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("("));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("(("));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("["));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("{[("));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("{[()}"));
        Assertions.assertFalse(CorrectBracketSequenceChecker.checkSequence("}"));
    }

    @Test
    public void testSequenceException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CorrectBracketSequenceChecker.checkSequence(" ");
        });

        StringBuilder string101symbol = new StringBuilder(101);
        for (int i = 0; i < 102; i++) {
            string101symbol.append('(');
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CorrectBracketSequenceChecker.checkSequence(string101symbol.toString());
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CorrectBracketSequenceChecker.checkSequence("()[-]");
        });
    }

    @Test
    public void testIsOpenBracket() {
        Assertions.assertTrue(CorrectBracketSequenceChecker.isBracket('(', true));
        Assertions.assertTrue(CorrectBracketSequenceChecker.isBracket('[', true));
        Assertions.assertTrue(CorrectBracketSequenceChecker.isBracket('{', true));

        Assertions.assertFalse(CorrectBracketSequenceChecker.isBracket('-', true));
        Assertions.assertFalse(CorrectBracketSequenceChecker.isBracket(' ', true));

        Assertions.assertFalse(CorrectBracketSequenceChecker.isBracket(')', true));
        Assertions.assertFalse(CorrectBracketSequenceChecker.isBracket(']', true));
        Assertions.assertFalse(CorrectBracketSequenceChecker.isBracket('}', true));
    }

    @Test
    public void testIsCloseBracket() {
        Assertions.assertTrue(CorrectBracketSequenceChecker.isBracket(')', false));
        Assertions.assertTrue(CorrectBracketSequenceChecker.isBracket(']', false));
        Assertions.assertTrue(CorrectBracketSequenceChecker.isBracket('}', false));

        Assertions.assertFalse(CorrectBracketSequenceChecker.isBracket('-', false));
        Assertions.assertFalse(CorrectBracketSequenceChecker.isBracket(' ', false));

        Assertions.assertFalse(CorrectBracketSequenceChecker.isBracket('(', false));
        Assertions.assertFalse(CorrectBracketSequenceChecker.isBracket('[', false));
        Assertions.assertFalse(CorrectBracketSequenceChecker.isBracket('{', false));
    }
}