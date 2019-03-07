package ru.mail.polis.open.task1.solution;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task1.FizzBuzz;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mikhail.nechaev
 * Since 04/03/2019
 */
class ConsoleFizzBuzzGameTest {

    private static final String DELIMITER = "-";
    private static PrintStream defaultSystemOut;
    private static ByteArrayOutputStream testBuffer;
    private static FizzBuzz fizzBuzz;

    @BeforeAll
    static void overrideSystemOut() {
        defaultSystemOut = System.out;
        testBuffer = new ByteArrayOutputStream();
        System.setOut(
            new PrintStream(
                new BufferedOutputStream(
                    testBuffer
                )
            )
        );
        fizzBuzz = new ConsoleFizzBuzzGame(DELIMITER);
    }

    @Test
    void testPrint() {
        fizzBuzz.print(0, 5);
        assertEquals(
            new StringJoiner(DELIMITER)
                .add("FizzBuzz")
                .add("1")
                .add("2")
                .add("Fizz")
                .add("4")
                .add("Buzz")
                .toString(),
            testBuffer.toString()
        );
    }

    @AfterEach
    void resetBuffer() {
        testBuffer.reset();
    }

    @AfterAll()
    static void resetOut() {
        System.setOut(defaultSystemOut);
    }
}