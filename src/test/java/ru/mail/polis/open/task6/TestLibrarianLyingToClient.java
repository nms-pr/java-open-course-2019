package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.physics;

public class TestLibrarianLyingToClient {
    @Test
    void testCheatingLibrarian() {
        Librarian jackLibrarian = new Librarian("Jack");
        LibraryClient samClient = new LibraryClient("Sam");
        assertThrows(IllegalCallerException.class, () -> jackLibrarian.getBookBackFromClient(physics, samClient));
    }
}
