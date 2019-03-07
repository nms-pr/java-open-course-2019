package ru.mail.polis.open.task1.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleOutputControllerTest {

    private static final char DELIMITER = ' ';

    private static OutputStream defaultOutputStream;
    private static ByteArrayOutputStream testOutputStream;
    private static ConsoleOutputController outputController;

    @BeforeAll
    static void initialize() {

        outputController = new ConsoleOutputController(DELIMITER);
        testOutputStream = new ByteArrayOutputStream();
        defaultOutputStream = System.out;
        System.setOut(new PrintStream(testOutputStream));
    }

    @Test
    void print() {
        final String testString1 = "testString!";
        outputController.print(testString1);
        assertEquals(testString1 + DELIMITER, testOutputStream.toString());
        final String testString2 = "Another one!!!";
        outputController.print(testString2);
        assertEquals(testString1 + DELIMITER + testString2 + DELIMITER, testOutputStream.toString());
    }

    @AfterEach
    void clearBuffer() {
        testOutputStream.reset();
    }

    @AfterAll
    static void restoreOutputStream() {
        System.setOut(new PrintStream(defaultOutputStream));
    }
}