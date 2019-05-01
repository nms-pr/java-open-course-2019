package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.peterClient;
import static ru.mail.polis.open.task6.UtilTest.winnie;

public class TestClientWithNoLibrarian {
    @Test
    void testClient() {
        assertThrows(IllegalStateException.class, () -> peterClient.takeBook(winnie));
    }
}
