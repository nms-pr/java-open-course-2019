package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.winnie;

public class TestClientWithNoLibrarian {
    @Test
    void testClient() {
        LibraryClient peterClient = new LibraryClient("Peter");
        assertThrows(IllegalStateException.class, () -> peterClient.takeBook(winnie));
    }
}
