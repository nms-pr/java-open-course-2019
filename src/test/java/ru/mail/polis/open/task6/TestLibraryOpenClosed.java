package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestLibraryOpenClosed {
    @Test
    void testLibraryOpenClosed() {
        LibraryClient samClient = new LibraryClient("Sam");
        Manager jackManager = new Manager("Jack");
        assertThrows(IllegalStateException.class, samClient::askLibrarian);
        jackManager.openLibrary();
        assertDoesNotThrow(samClient::askLibrarian);
        Manager paulManager = new Manager("Paul");
        paulManager.closeLibrary();
        LibraryClient dannyClient = new LibraryClient("Danny");
        assertThrows(IllegalStateException.class, dannyClient::askLibrarian);
    }
}
