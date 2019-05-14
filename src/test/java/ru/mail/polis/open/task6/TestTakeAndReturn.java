package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.winnie;
import static ru.mail.polis.open.task6.UtilTest.physics;
import static ru.mail.polis.open.task6.UtilTest.narnia1;
import static ru.mail.polis.open.task6.UtilTest.narnia2;
import static ru.mail.polis.open.task6.UtilTest.myths;
import static ru.mail.polis.open.task6.UtilTest.winnie2;

public class TestTakeAndReturn {
    @Test
    void testTakeAndReturn() {
        Librarian lib1 = new Librarian("John");
        Librarian lib2 = new Librarian("Helen");
        Librarian lib3 = new Librarian("Samantha");
        Manager jackManager = new Manager("Jack");
        Manager paulManager = new Manager("Paul");
        paulManager.openLibrary();
        paulManager.addNewBookToStore(winnie, 13);
        jackManager.addNewBookToStore(winnie, 3);
        jackManager.addNewBookToStore(physics, 23);
        paulManager.addNewBookToStore(narnia1, 3);
        paulManager.addNewBookToStore(narnia2);
        paulManager.addNewBookToStore(myths);
        jackManager.addNewBookToStore(winnie2);
        LibraryClient peterClient = new LibraryClient("Peter");
        peterClient.askLibrarian();
        peterClient.takeBook(1,
                "Winnie the Pooh",
                new ArrayList<>(Arrays.asList("Fairytale", "Children", "Alan Milne")));
        LibraryClient samClient = new LibraryClient("Sam");
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
