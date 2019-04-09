package ru.mail.polis.open.task6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task6.library.Book;
import ru.mail.polis.open.task6.library.Librarian;
import ru.mail.polis.open.task6.library.Library;
import ru.mail.polis.open.task6.library.Manager;

public class LibraryTest {
    @Test
    void test() {
        Manager manager = new Manager("Sasha");
        Librarian librarian = new Librarian("Liza");
        Customer pavel = new Customer("Pavel", "Malyshev");

        Library library = new Library(manager, librarian);

        manager.linkWithLibrary(library);
        librarian.linkWithLibrary(library);
        pavel.linkWithLibrary(library);

        assertEquals(true, library.isOpen());

        Book java8 = new Book(1, "Java8", "Programming", 1);
        Book c = new Book(2, "C", "Programming", 1);
        Book java11 = new Book(1, "Java11", "Programming", 1);
        Book kotlin = new Book(3, "Kotlin", "Programming", 1);
        Book html = new Book(4, "HTML", "Programming", 1);
        manager.put(java8);
        manager.put(java11);
        manager.put(c);
        manager.put(kotlin);
        manager.put(html);
        assertEquals(5, library.getAvailableBooks().size());

        assertEquals(java8, pavel.get("Java8"));
        assertEquals(java11, pavel.get("Java11"));
        assertEquals(3, library.getAvailableBooks().size());
        assertEquals(2, pavel.getMyBooks().size());
        pavel.put("Java8");
        assertEquals(1, pavel.getMyBooks().size());
        assertEquals(4, library.getAvailableBooks().size());

        /*assertThrows(
                IllegalArgumentException.class,
                () -> manager.remove(Java8)
        );*/
    }
}
