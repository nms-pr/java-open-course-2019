package ru.mail.polis.open.task3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task1.FizzBuzz;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

class CorrectBracketSequenceChekerTest {

    @Test
    void testFirstCheckLastBrackString() {
        String last = CorrectBracketSequenceChecker.getLastSuccessSequence();

        assertNull(last);
    }

    @Test
    void testCheckNullBrackString() {
        String[] input = new String[] {"(((())", "()()(()()", "((((((())))))(", "(()(()())", "(((()))"};

        for(int i = 0; i< input.length; i++) {
            CorrectBracketSequenceChecker.checkSequence(input[i]);
        }

        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
    }

    @Test
    void testEmptyStringIsCorrectBrackString() {
        String input = "";

        boolean flag = CorrectBracketSequenceChecker.checkSequence(input);

        assertTrue(flag);
    }

    @Test
    void testSinglIsCorrectBrackString() {
        String input = "((((()()))))";

        boolean flag = CorrectBracketSequenceChecker.checkSequence(input);

        assertTrue(flag);
    }

    @Test
    void testSinglIsFailBrackString() {
        String input = "(((())";

        boolean flag = CorrectBracketSequenceChecker.checkSequence(input);

        assertFalse(flag);
    }

    @Test
    void testException() {
        String input = "((((9))";
        String inputSecond = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100 101 102 103 104 105 106 107 108 109 ";


        assertThrows(
                IllegalArgumentException.class,
                () -> CorrectBracketSequenceChecker.checkSequence(input)
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> CorrectBracketSequenceChecker.checkSequence(inputSecond)
        );
    }

    @Test
    void testManyBrackString() {
        String[] input = new String[] {"(((())", "()()()()", "((((((())))))(", "()(()())", "((()))"};

        for(int i = 0; i< input.length; i++) {
            CorrectBracketSequenceChecker.checkSequence(input[i]);
        }

        assertEquals(3, CorrectBracketSequenceChecker.getSuccessChecksCount());
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());
    }
}