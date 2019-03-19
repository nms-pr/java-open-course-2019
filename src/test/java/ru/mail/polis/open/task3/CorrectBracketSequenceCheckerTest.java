package ru.mail.polis.open.task3;

        import org.junit.jupiter.api.Test;


        import static org.junit.jupiter.api.Assertions.*;

        public class CorrectBracketSequenceCheckerTest {

        @Test
        void isBracketOpening() {
        assertTrue(CorrectBracketSequenceChecker.isOpening('{'));
        assertTrue(CorrectBracketSequenceChecker.isOpening('['));
        assertTrue(CorrectBracketSequenceChecker.isOpening('('));
        assertFalse(CorrectBracketSequenceChecker.isOpening('a'));
        assertFalse(CorrectBracketSequenceChecker.isOpening('}'));
        }

        @Test
        void isBracketClosing() {
        assertTrue(CorrectBracketSequenceChecker.isClosing('}'));
        assertTrue(CorrectBracketSequenceChecker.isClosing(']'));
        assertTrue(CorrectBracketSequenceChecker.isClosing(')'));
        assertFalse(CorrectBracketSequenceChecker.isClosing('c'));
        assertFalse(CorrectBracketSequenceChecker.isClosing('{'));
        }

        @Test
        void arePair() {
        assertTrue(CorrectBracketSequenceChecker.arePair('[', ']'));
        assertTrue(CorrectBracketSequenceChecker.arePair('(', ')'));
        assertTrue(CorrectBracketSequenceChecker.arePair('{', '}'));
        assertFalse(CorrectBracketSequenceChecker.arePair('{', ']'));
        assertFalse(CorrectBracketSequenceChecker.arePair('a', ']'));
        }

        @Test
        void checkValidSequence() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(null));
        assertTrue(CorrectBracketSequenceChecker.checkSequence(""));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[{()}]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[(){}]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[{}]([]{})"));
        }

        @Test
        void checkInvalidSequence() {
        assertFalse(CorrectBracketSequenceChecker.checkSequence("[)"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(){]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({}[))"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({(]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(])"));
        assertThrows(
        IllegalArgumentException.class,
        () -> CorrectBracketSequenceChecker.checkSequence("{a}")
        );
        }

        @Test
        void successChecksNumber() {
        CorrectBracketSequenceChecker.checkSequence(null);
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("");
        assertEquals(2, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("[{()}]");
        assertEquals(3, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("[(){}]");
        assertEquals(4, CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("[{}]([]{})");
        assertEquals(5, CorrectBracketSequenceChecker.getSuccessChecksCount());
        }

        @Test
        void failedChecksNumber() {
        CorrectBracketSequenceChecker.checkSequence("[)");
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence("(){]");
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence("({}[))");
        assertEquals(3, CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence("({(]");
        assertEquals(4, CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence("(])");
        assertEquals(5, CorrectBracketSequenceChecker.getFailChecksCount());
        }

        @Test
        void lastSuccessPairInValidSequence() {
        CorrectBracketSequenceChecker.checkSequence(null);
        assertEquals("", CorrectBracketSequenceChecker.getLastSuccessSequence());
        CorrectBracketSequenceChecker.checkSequence("");
        assertEquals("", CorrectBracketSequenceChecker.getLastSuccessSequence());
        CorrectBracketSequenceChecker.checkSequence("[{()}]");
        assertEquals("[{()}]", CorrectBracketSequenceChecker.getLastSuccessSequence());
        CorrectBracketSequenceChecker.checkSequence("[(){}]()");
        assertEquals("[(){}]()", CorrectBracketSequenceChecker.getLastSuccessSequence());
        assertFalse(CorrectBracketSequenceChecker.checkSequence("[()"));
        assertEquals("[(){}]()", CorrectBracketSequenceChecker.getLastSuccessSequence());
        }
        }
