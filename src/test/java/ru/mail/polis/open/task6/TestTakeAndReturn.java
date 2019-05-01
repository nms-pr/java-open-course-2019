package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.paulManager;
import static ru.mail.polis.open.task6.UtilTest.jackManager;
import static ru.mail.polis.open.task6.UtilTest.peterClient;
import static ru.mail.polis.open.task6.UtilTest.samClient;
import static ru.mail.polis.open.task6.UtilTest.winnie;
import static ru.mail.polis.open.task6.UtilTest.physics;
import static ru.mail.polis.open.task6.UtilTest.narnia1;
import static ru.mail.polis.open.task6.UtilTest.narnia2;
import static ru.mail.polis.open.task6.UtilTest.myths;
import static ru.mail.polis.open.task6.UtilTest.winnie2;

public class TestTakeAndReturn {
    @Test
    void testTakeAndReturn() {
        paulManager.openLibrary();
        paulManager.addNewBookToStore(winnie, 13);
        jackManager.addNewBookToStore(winnie, 3);
        jackManager.addNewBookToStore(physics, 23);
        paulManager.addNewBookToStore(narnia1, 3);
        paulManager.addNewBookToStore(narnia2);
        paulManager.addNewBookToStore(myths);
        jackManager.addNewBookToStore(winnie2);
        peterClient.askLibrarian();
        peterClient.takeBook(1,
                "Winnie the Pooh",
                new ArrayList<>(Arrays.asList("Fairytale", "Children", "Alan Milne")));
        samClient.askLibrarian();
        samClient.takeBook(narnia2);
        assertEquals(1, samClient.takeBook(physics));
        samClient.takeBook(winnie);
        assertEquals(-1, peterClient.takeBook(winnie2));
        assertEquals(15, ManagingPerson.store.get(winnie));
        assertThrows(NullPointerException.class, () -> ManagingPerson.store.get(narnia2));
        assertEquals(22, ManagingPerson.store.get(physics));
        samClient.sayGoodbie();
        peterClient.returnBook(winnie);
        assertEquals(16, ManagingPerson.store.get(winnie));
    }
}
