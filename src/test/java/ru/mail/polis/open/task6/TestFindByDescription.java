package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.mail.polis.open.task6.UtilTest.narnia1;
import static ru.mail.polis.open.task6.UtilTest.winnie;
import static ru.mail.polis.open.task6.UtilTest.myths;
import static ru.mail.polis.open.task6.UtilTest.physics;
import static ru.mail.polis.open.task6.UtilTest.narnia2;

public class TestFindByDescription {

    private <T> void assertArrayLists(ArrayList<T> expected, ArrayList<T> actual) {
        if (expected.size() != actual.size()) {
            throw new AssertionFailedError();
        }
        for (T act: actual) {
            expected.removeIf((o) -> o.equals(act));
        }
        assertEquals(0, expected.size());
    }

    @Test
    void testFindBookByDescr() {
        Manager paulManager = new Manager("Paul");
        Manager jackManager = new Manager("Jack");
        jackManager.addNewBookToStore(narnia1, 17);
        paulManager.addNewBookToStore(winnie, 2);
        paulManager.addNewBookToStore(myths, 3);
        jackManager.addNewBookToStore(physics, 21);
        jackManager.addNewBookToStore(narnia2);
        Librarian annaLibrarian = new Librarian("Anna");
        assertEquals(annaLibrarian.findBookByFullDescription(2,
                "Chronicles of Narnia",
                new ArrayList<>(Arrays.asList("Fairytale", "Family", "Children", "Mythology", "Part 1"))), narnia1);
        Librarian peterLibrarian = new Librarian("Peter");
        ArrayList<Book> byName =
                peterLibrarian.findAllBooksByParameter("Chronicles of Narnia",
                        Person.BookSearchParameter.BY_NAME);
        ArrayList<Book> correctByName = new ArrayList<>(Arrays.asList(narnia2, narnia1));
        assertArrayLists(correctByName, byName);
        ArrayList<Book> byId = annaLibrarian.findAllBooksByParameter(1, Person.BookSearchParameter.BY_ID);
        ArrayList<Book> correctById = new ArrayList<>(Collections.singletonList(winnie));
        assertArrayLists(correctById, byId);
        ArrayList<Book> byPartition =
                annaLibrarian.findAllBooksByParameter("Education",
                        Person.BookSearchParameter.BY_PARTITION);
        ArrayList<Book> correctByPartition = new ArrayList<>(Arrays.asList(myths, physics));
        assertArrayLists(correctByPartition, byPartition);
        Librarian jackLibrarian = new Librarian("Jack");
        ArrayList<Book> byPartitions =
                jackLibrarian.findAllBooksByParameter(
                        new ArrayList<>(Arrays.asList("Fairytale", "Family")),
                        Person.BookSearchParameter.BY_PARTITIONS);
        ArrayList<Book> correctByPartitions = new ArrayList<>(Arrays.asList(narnia2, narnia1));
        assertArrayLists(correctByPartitions, byPartitions);
    }
}
