package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OneDayTest {
    private static Book book = new Book("Кукушка", "Цой", "Лирика", 1,1);
    private static Book book1 = new Book("Былина о Java", "Шилдт", "Былины", 2,2);
    private static Book book3 = new Book("Пособие для поступления на основную программу", "Секрет", "Фантастика", 3,3);
    private static SomeVisitor visitor = new SomeVisitor("Вася", 20, 'M');

    @Test
    void oneDay() {
        Library.manager.openLibrary();
        assertEquals(true, Library.getLibStatus());
        assertEquals(9, Library.getAllBooks().size());
        Library.librarian.giveOutBook("1984", visitor);
        assertEquals(8, Library.getAllBooks().size());
        assertThrows(IllegalArgumentException.class, () -> visitor.getBook("1984"));
        Library.manager.addBook(book);
        assertEquals(9, Library.getAllBooks().size());
        Library.librarian.takeBookIn(visitor.getTakenBooks().get(0), visitor);
        assertEquals(10, Library.getAllBooks().size());
        assertEquals(0, Library.getBusyBooksInLib().size());
        assertThrows(IllegalArgumentException.class, () -> visitor.getBook("Былина о Java"));
        Library.manager.addBook(book1);
        assertEquals(book1, Library.librarian.findBook("Былина о Java"));
        Library.manager.closeLibrary();
    }

    @AfterAll
    static void clear() {
        Library.setLibStatus(false);
        Library.getBusyBooksInLib().clear();
        Library.getAllBooks().clear();
        Library.getAllShelfsInLib().clear();
    }
}
