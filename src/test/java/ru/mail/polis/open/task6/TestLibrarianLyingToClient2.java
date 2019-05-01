package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.annaLibrarian;
import static ru.mail.polis.open.task6.UtilTest.dannyClient;
import static ru.mail.polis.open.task6.UtilTest.winnie;

public class TestLibrarianLyingToClient2 {
    @Test
    void testCheatingLibrarian4() {
        assertThrows(IllegalCallerException.class, () -> annaLibrarian.newBookInfo(winnie, dannyClient));
    }
}
