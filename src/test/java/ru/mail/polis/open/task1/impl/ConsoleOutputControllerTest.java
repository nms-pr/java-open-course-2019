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

    private static OutputStream defaultOutputStream;
    private static ByteArrayOutputStream testOutputStream;

    @BeforeAll
    static void prepareOutputStream() {
        defaultOutputStream = System.out;
        testOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutputStream));
    }

    @Test
    void print() {
        assertEquals("testString!", testOutputStream.toString());
        assertEquals("Another one!!!", testOutputStream.toString());
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