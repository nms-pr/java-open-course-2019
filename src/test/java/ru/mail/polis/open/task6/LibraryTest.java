package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task6.library.Book;
import ru.mail.polis.open.task6.library.Librarian;
import ru.mail.polis.open.task6.library.Library;
import ru.mail.polis.open.task6.library.Manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryTest {

    @Test
    void test() {
        Manager manager = new Manager("A");
        Librarian librarian = new Librarian("B");
        Library library = new Library(manager, librarian);
        Customer pavel = new Customer("Pavel", "CCC");
        Customer sasha = new Customer("Alexandra", "AAA");

        pavel.linkWithLibrary(library);
        sasha.linkWithLibrary(library);
        manager.linkWithLibrary(library);
        librarian.linkWithLibrary(library);
        
        final Book java8 = new Book(1, "Java8", "programming", 1);
        final Book martinEden = new Book(1, "Martin Eden", "fiction", 1);
        final Book c = new Book(2, "C", "programming", 1);

        //MANAGER TEST
        assertTrue(library.isOpen());
        // проверим закрыта ли билиотека
        manager.close();
        assertFalse(library.isOpen());
        //попробуем положить.удалить книги
        assertThrows(
            IllegalArgumentException.class,
            () -> manager.put(java8)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> manager.remove(java8)
        );
        manager.open();
        assertTrue(library.isOpen());

        //открыли, добавляем проверяем кол-во доступных
        manager.put(java8);
        assertEquals(1, library.getAvailableBooks().size());
        manager.put(martinEden);
        assertEquals(2, library.getAvailableBooks().size());
        //удаляем, проверяем доступные
        manager.remove(martinEden);
        assertEquals(1, library.getAvailableBooks().size());
        //удаляем книгу из несуществующей секции
        assertThrows(
            IllegalArgumentException.class,
            () -> manager.remove(martinEden)
        );
        //удаляем несуществующую книгу
        assertThrows(
            IllegalArgumentException.class,
            () -> manager.remove(c)
        );
        //пришёл клиент,взял книгу
        pavel.takeBook("programming", "Java8");
        //оп,не можем удалить взятую книгу
        assertThrows(
            IllegalArgumentException.class,
            () -> manager.remove(java8)
        );

        //CUSTOMER TEST
        manager.close();
        //клиент не может взять.вернуть книгу,пока библиотека закрыта
        assertThrows(
            IllegalArgumentException.class,
            () -> pavel.takeBook("programming", "Java8")
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> pavel.returnBook(java8)
        );

        manager.open();
        //не может вернуть книгу,которую не брал(или не у нас)
        assertThrows(
            IllegalArgumentException.class,
            () -> pavel.returnBook(martinEden)
        );
        //хочет взять книгу непонятной секции какой-то, у нас такой нет
        assertThrows(
            IllegalArgumentException.class,
            () -> pavel.takeBook("fiction", "Martin Eden")
        );
        //и книги такой у нас нет по программированию
        assertThrows(
            IllegalArgumentException.class,
            () -> pavel.takeBook("programming", "Martin Eden")
        );
        manager.put(martinEden);
        assertEquals(1, library.getAvailableBooks().size());
        //вот теперь взял,прочёл,вернул - молодец (другим при этом недоступна)
        pavel.takeBook("fiction", "Martin Eden");
        assertEquals(0, library.getAvailableBooks().size());
        //никто другой её взять не может
        assertThrows(
            IllegalArgumentException.class,
            () -> sasha.takeBook("fiction", "Martin Eden")
        );
        pavel.returnBook(martinEden);
        assertEquals(1, library.getAvailableBooks().size());
        library.listOfTakenBooks();
    }
}
