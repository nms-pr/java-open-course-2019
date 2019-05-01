package ru.mail.polis.open.task6;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Tests {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private Manager jackManager;
    private Manager paulManager;
    private Librarian jackLibrarian;
    private Librarian annaLibrarian;
    private Librarian peterLibrarian;
    private LibraryClient peterClient;
    private LibraryClient samClient;
    private LibraryClient dannyClient;
    private Book winnie;
    private Book narnia1;
    private Book myths;
    private Book physics;
    private Book narnia2;
    private Book winnie2;

    @BeforeAll
    void init() {
        jackManager = new Manager("Jack");
        paulManager = new Manager("Paul");
        jackLibrarian = new Librarian("Jack");
        annaLibrarian = new Librarian("Anna");
        peterLibrarian = new Librarian("Peter");
        peterClient = new LibraryClient("Peter");
        samClient = new LibraryClient("Sam");
        dannyClient = new LibraryClient("Danny");
        winnie = new Book(1,
                "Winnie the Pooh",
                new ArrayList<>(Arrays.asList("Fairytale", "Children", "Alan Milne")));
        narnia1 = new Book(2,
                "Chronicles of Narnia",
                new ArrayList<>(Arrays.asList("Fairytale", "Family", "Children", "Mythology", "Part 1")));
        myths = new Book(3,
                "Myths of Ancient Greece",
                new ArrayList<>(Arrays.asList("Mythology", "Education")));
        physics = new Book(4,
                "Quantum Physics for stupid people",
                new ArrayList<>(Arrays.asList("Science", "Education")));
        narnia2 = new Book(5,
                "Chronicles of Narnia",
                new ArrayList<>(Arrays.asList("Fairytale", "Family", "Children", "Mythology", "Part 2")));
        winnie2 = new Book(1,
                "Winnie the Pooh",
                new ArrayList<>(Arrays.asList("Fairytale", "Children", "Alan Milne")));
    }

    private <T> void assertArrayLists(ArrayList<T> expected, ArrayList<T> actual) {
        for (int i = 0; i < expected.size() ; i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    void testBook() {
        assertEquals("Chronicles of Narnia", narnia1.getName());
        assertEquals(4, physics.getId());
        assertEquals("[Mythology, Education]", Arrays.toString(myths.getPartitions().toArray()));
        assertEquals("Id: 1; Name: Winnie the Pooh"
                + LINE_SEPARATOR
                + "Partitions: Fairytale; Children; Alan Milne", winnie.toString());
        assertEquals(winnie, winnie2);
    }

    @Test
    void testPersonNaming() {
        assertEquals("Paul", paulManager.getName());
        assertThrows(IllegalArgumentException.class, () -> new Manager("Jack"));
    }

    @Test
    void testHasMapOperating() {
        assertThrows(IllegalCallerException.class,
                () -> jackLibrarian.bookHashMapOperating(new ManagingPerson.Store(), winnie2, 13));
    }

    @Test
    void testAddOneBook() {
        assertThrows(IllegalCallerException.class,
                () -> samClient.addOneBookToCollection(narnia1, new ArrayList<Book>()));
    }

    @Test
    void testRemoveOneBook() {
        assertThrows(IllegalCallerException.class,
                () -> annaLibrarian.removeOneBookFromCollection(physics, ManagingPerson.store));
    }

    @Test
    void testIllegalPut() {
        assertThrows(IllegalCallerException.class, () -> ManagingPerson.store.put(narnia2, 12));
    }

    @Test
    void testIllegalRemove() {
        jackManager.addNewBookToStore(myths, 12);
        assertThrows(IllegalCallerException.class, () -> ManagingPerson.store.remove(myths));
    }

    @Test
    void testManagerAddBook() {
        paulManager.addNewBookToStore(winnie, 11);
        assertTrue(ManagingPerson.store.containsKey(winnie) && ManagingPerson.store.get(winnie) == 11);
        jackManager.addNewBookToStore(narnia1);
        assertTrue(ManagingPerson.store.containsKey(narnia1) && ManagingPerson.store.get(narnia1) == 1);
        paulManager.addNewBookToStore(narnia1, 4);
        assertTrue(ManagingPerson.store.containsKey(narnia1) && ManagingPerson.store.get(narnia1) == 5);
    }

    @Test
    void testLibraryOpenClosed() {
        assertThrows(IllegalStateException.class, () -> samClient.askLibrarian());
        jackManager.openLibrary();
        assertDoesNotThrow(() -> samClient.askLibrarian());
        paulManager.closeLibrary();
        assertThrows(IllegalStateException.class, () -> dannyClient.askLibrarian());
    }

    @Test
    void testFindBookByDescr() {
        jackManager.addNewBookToStore(narnia1, 17);
        paulManager.addNewBookToStore(winnie, 2);
        paulManager.addNewBookToStore(myths, 3);
        jackManager.addNewBookToStore(physics, 21);
        jackManager.addNewBookToStore(narnia2);
        assertEquals(annaLibrarian.findBookByFullDescription(2,
                "Chronicles of Narnia",
                new ArrayList<>(Arrays.asList("Fairytale", "Family", "Children", "Mythology", "Part 1"))), narnia1);
        ArrayList<Book> byName =
                jackLibrarian.findAllBooksByParameter("Chronicles of Narnia",
                        Person.BookSearchParameter.BY_NAME);
        ArrayList<Book> correctByName = new ArrayList<>(Arrays.asList(narnia2, narnia1));
        assertArrayLists(correctByName, byName);
        ArrayList<Book> byId = annaLibrarian.findAllBooksByParameter(1, Person.BookSearchParameter.BY_ID);
        ArrayList<Book> correctById = new ArrayList<>(Collections.singletonList(winnie));
        assertArrayLists(correctById, byId);
        ArrayList<Book> byPartition =
                annaLibrarian.findAllBooksByParameter("Education",
                        Person.BookSearchParameter.BY_PARTITION);
        ArrayList<Book> correctByPartition = new ArrayList<>(Arrays.asList(physics, myths));
        assertArrayLists(correctByPartition, byPartition);
        ArrayList<Book> byPartitions =
                jackLibrarian.findAllBooksByParameter(
                        new ArrayList<>(Arrays.asList("Fairytale", "Family")),
                        Person.BookSearchParameter.BY_PARTITIONS);
        ArrayList<Book> correctByPartitions = new ArrayList<>(Arrays.asList(narnia2, narnia1));
        assertArrayLists(correctByPartitions, byPartitions);
    }

    @Test
    void testCheatingLibrarian1() {
        assertThrows(IllegalCallerException.class, Librarian::getAFreeLibrarian);

    }

    @Test
    void testCheatingLibrarian2() {
        paulManager.openLibrary();
        dannyClient.askLibrarian();
        assertThrows(IllegalCallerException.class, () -> jackLibrarian.becomeFree());
    }

    @Test
    void testCheatingLibrarian3() {
        assertThrows(IllegalCallerException.class, () -> jackLibrarian.getBookBackFromClient(physics, samClient));
    }

    @Test
    void testCheatingLibrarian4() {
        assertThrows(IllegalCallerException.class, () -> annaLibrarian.newBookInfo(winnie, dannyClient));
    }
}
