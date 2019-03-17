package ru.mail.polis.open.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CorrectBracketSequenceCheckerTest {
    private static final String[] correctSequences = {
        "", "()[]{}", "([]){}", "{[()][]({})}", "([{([{}])}])"
    };
    private static final String[] incorrectSequences = {
        ")", "(", "}{}()", "{()[]", "{{}", "([{([{])}])"
    };
    private static final char OPEN_PARENTHESIS = '(';
    private static final char CLOSE_PARENTHESIS = ')';
    private static final char OPEN_SQUARE_BRACKET = '[';
    private static final char CLOSE_SQUARE_BRACKET = ']';
    private static final char OPEN_BRACE = '{';
    private static final char CLOSE_BRACE = '}';


    @Test
    void positiveIsOpenBracket() {
        assertTrue(CorrectBracketSequenceChecker.isOpenBracket(OPEN_PARENTHESIS));
        assertTrue(CorrectBracketSequenceChecker.isOpenBracket(OPEN_SQUARE_BRACKET));
        assertTrue(CorrectBracketSequenceChecker.isOpenBracket(OPEN_BRACE));
    }

    @Test
    void negativeIsOpenBracket() {
        assertFalse(CorrectBracketSequenceChecker.isOpenBracket(CLOSE_PARENTHESIS));
        assertFalse(CorrectBracketSequenceChecker.isOpenBracket(CLOSE_SQUARE_BRACKET));
        assertFalse(CorrectBracketSequenceChecker.isOpenBracket(CLOSE_BRACE));
        assertFalse(CorrectBracketSequenceChecker.isOpenBracket('太'));
    }

    @Test
    void positiveIsCloseBracket() {
        assertTrue(CorrectBracketSequenceChecker.isCloseBracket(CLOSE_PARENTHESIS));
        assertTrue(CorrectBracketSequenceChecker.isCloseBracket(CLOSE_SQUARE_BRACKET));
        assertTrue(CorrectBracketSequenceChecker.isCloseBracket(CLOSE_BRACE));
    }

    @Test
    void negativeIsCloseBracket() {
        assertFalse(CorrectBracketSequenceChecker.isCloseBracket(OPEN_PARENTHESIS));
        assertFalse(CorrectBracketSequenceChecker.isCloseBracket(OPEN_SQUARE_BRACKET));
        assertFalse(CorrectBracketSequenceChecker.isCloseBracket(OPEN_BRACE));
        assertFalse(CorrectBracketSequenceChecker.isCloseBracket('太'));
    }

    @Test
    void positiveIsBracketsAnnihilates() {
        assertTrue(CorrectBracketSequenceChecker.isBracketsAnnihilates(CLOSE_PARENTHESIS,
                OPEN_PARENTHESIS));
        assertTrue(CorrectBracketSequenceChecker.isBracketsAnnihilates(CLOSE_SQUARE_BRACKET,
                OPEN_SQUARE_BRACKET));
        assertTrue(CorrectBracketSequenceChecker.isBracketsAnnihilates(CLOSE_BRACE, OPEN_BRACE));
    }

    @Test
    void negativeIsBracketsAnnihilates() {
        assertFalse(CorrectBracketSequenceChecker.isBracketsAnnihilates(OPEN_PARENTHESIS,
                CLOSE_PARENTHESIS));
        assertFalse(CorrectBracketSequenceChecker.isBracketsAnnihilates(CLOSE_BRACE, '1'));
        assertFalse(CorrectBracketSequenceChecker.isBracketsAnnihilates('1', OPEN_BRACE));
    }

    @Test
    void positiveCheckSequence() {
        for (String seq : correctSequences) {
            assertTrue(CorrectBracketSequenceChecker.checkSequence(seq));
        }
    }

    @Test
    void negativeCheckSequence() {
        for (String seq : incorrectSequences) {
            assertFalse(CorrectBracketSequenceChecker.checkSequence(seq));
        }
    }

    @Test
    void nullCheckSequence() {
        assertThrows(IllegalArgumentException.class, () -> {
            CorrectBracketSequenceChecker.checkSequence(null);
        });
    }

    @Test
    void sequenceShouldBeLessThan100() {
        int n = 102;
        char[] chars = new char[n];
        for (int i = 0; i < n; i++) {
            chars[i] = i % 2 == 0 ? OPEN_BRACE : CLOSE_BRACE;
        }
        String result = new String(chars);

        assertThrows(IllegalArgumentException.class, () -> {
            CorrectBracketSequenceChecker.checkSequence(result);
        });
    }

    @Test
    void sequenceShouldNotContainAnyNonBracketCharacter() {
        assertThrows(IllegalArgumentException.class, () -> {
            CorrectBracketSequenceChecker.checkSequence("a");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            CorrectBracketSequenceChecker.checkSequence(OPEN_BRACE + "a");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            CorrectBracketSequenceChecker.checkSequence(correctSequences[2] + "a");
        });
    }

    @Test
    void positiveLastSuccessSequence() {
        CorrectBracketSequenceChecker.init();

        for (String seq : correctSequences) {
            CorrectBracketSequenceChecker.checkSequence(seq);
            assertEquals(seq, CorrectBracketSequenceChecker.getLastSuccessSequence());
        }
    }

    @Test
    void negativeLastSuccessSequence() {
        CorrectBracketSequenceChecker.init();

        for (String seq : incorrectSequences) {
            CorrectBracketSequenceChecker.checkSequence(seq);
            assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
        }

        CorrectBracketSequenceChecker.checkSequence(correctSequences[0]);
        CorrectBracketSequenceChecker.checkSequence(incorrectSequences[0]);
        assertEquals(correctSequences[0], CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence(correctSequences[2]);
        CorrectBracketSequenceChecker.checkSequence(incorrectSequences[3]);
        assertEquals(correctSequences[2], CorrectBracketSequenceChecker.getLastSuccessSequence());
    }

    @Test
    void getSuccessChecksCount() {
        CorrectBracketSequenceChecker.init();

        for (String seq : correctSequences) {
            CorrectBracketSequenceChecker.checkSequence(seq);
        }
        for (String seq : incorrectSequences) {
            CorrectBracketSequenceChecker.checkSequence(seq);
        }

        assertEquals(correctSequences.length,
                CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void getFailChecksCount() {
        CorrectBracketSequenceChecker.init();

        for (String seq : incorrectSequences) {
            CorrectBracketSequenceChecker.checkSequence(seq);
        }

        assertEquals(0, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }
}
