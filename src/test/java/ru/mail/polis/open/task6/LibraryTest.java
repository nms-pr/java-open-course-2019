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
    void test(){
        Manager manager = new Manager("Sasha");
        Librarian librarian = new Librarian("Liza");
        Customer Pavel = new Customer("Pavel","Malyshev");

        Library library = new Library(manager,librarian);

        manager.linkWithLibrary(library);
        librarian.linkWithLibrary(library);
        Pavel.linkWithLibrary(library);

        assertEquals(true,library.isOpen());

        Book Java8 = new Book(1,"Java8","Programming",1);
        Book C = new Book(2,"C","Programming",1);
        Book Java11 = new Book(1,"Java11","Programming",1);
        Book Kotlin = new Book(3,"Kotlin","Programming",1);
        Book HTML = new Book(4,"HTML","Programming",1);
        manager.put(Java8);
        manager.put(Java11);
        manager.put(C);
        manager.put(Kotlin);
        manager.put(HTML);
        assertEquals(5,library.getAvailableBooks().size());

        assertEquals(Java8,Pavel.get("Java8"));
        assertEquals(Java11,Pavel.get("Java11"));
        assertEquals(3,library.getAvailableBooks().size());
        assertEquals(2,Pavel.getMyBooks().size());
        Pavel.put("Java8");
        assertEquals(1,Pavel.getMyBooks().size());
        assertEquals(4,library.getAvailableBooks().size());

        /*assertThrows(
                IllegalArgumentException.class,
                () -> manager.remove(Java8)
        );*/
    }
}
