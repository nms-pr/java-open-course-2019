package ru.mail.polis.open.task6.office;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task6.Genres;
import ru.mail.polis.open.task6.Guest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MainTest {

    private LibraryStorage libraryStorage;
    private Librarian librarian;
    private Manager manager;

    @BeforeEach
    void initialize() {
        libraryStorage = new LibraryStorage();
        librarian = new Librarian(libraryStorage);
        manager = new Manager(libraryStorage, librarian);
    }

    @Test
    void testInterconnection() {
        class DummyGuest extends Guest {

            private DummyGuest(Librarian librarian) {
                super(librarian);
            }

            @Override
            public List<Book> processAvailableBooks(List<Book> availableBooks) {
                if (availableBooks != null) {
                    return List.of(availableBooks.get(0));
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public GuestOrder makeAnOrder() {
                List<String> bookNames = List.of("Меч предназначения", "Пикник на обочине");
                List<Genres> genres = List.of(Genres.FICTION);
                return new GuestOrder(bookNames, genres, this);
            }
        }

        assertFalse(libraryStorage.isOpen());
        // менеджер приносит книги
        assertTrue(manager.putNewBookIntoLibrary(List.of(
            new Book("Меч предназначения", Genres.FICTION),
            new Book("Пикник на обочине", Genres.FICTION),
            new Book("Трансерфинг реальности", Genres.COMIC),
            new Book("Меч предназначения", Genres.DRAMA),
            new Book("Пикник на обочине", Genres.DRAMA)
        )));

        // приходит посетитель и пытается спросить, есть ли нужные ему книги
        Guest guest = new DummyGuest(librarian);
        GuestOrder order = guest.makeAnOrder();

        // получает null, потому что закрыто
        assertNull(guest.askTheLibrarianAboutOrder(order));

        // открываем библиотеку
        manager.openLibrary();
        assertTrue(libraryStorage.isOpen());

        // посетитель опять пришел и просит книги
        List<Book> available = guest.askTheLibrarianAboutOrder(order);
        assertEquals(
            List.of(new Book("Меч предназначения", Genres.FICTION),
                new Book("Пикник на обочине", Genres.FICTION)),
            available
        );

        // посетитель выбирает книги из предложенных
        List<Book> chosen = guest.processAvailableBooks(available);
        assertEquals(
            List.of(
                new Book("Меч предназначения", Genres.FICTION)),
            chosen
        );

        // библиотекарь модифицирует заказ в соответствии с выбранным и выдает книги
        librarian.giveBooks(chosen, order);
        assertEquals(
            List.of(
                new Book("Меч предназначения", Genres.FICTION)),
            order.getTakenBooks()
        );
        assertEquals(
            List.of(
                new Book("Меч предназначения", Genres.FICTION)),
            guest.getTakenBooks()
        );
        Map<Guest, GuestOrder> checkLedger = new HashMap<>();
        checkLedger.put(guest, order);
        assertEquals(
            checkLedger,
            librarian.getLedger()
        );

        // библиотека закрывается
        manager.closeLibrary();

        // посетитель хочет вернуть книги в закрытую библиотеку
        assertThrows(IllegalAccessException.class, () -> guest.returnTheBooks(order));
    }

    @Test
    void testSomeSpecials() {
        class SecondDummyGuest extends Guest {

            private SecondDummyGuest(Librarian librarian) {
                super(librarian);
            }

            @Override
            public List<Book> processAvailableBooks(List<Book> availableBooks) {
                if (availableBooks != null) {
                    return List.of(availableBooks.get(0));
                } else {
                    return null;
                }
            }

            @Override
            public GuestOrder makeAnOrder() {
                List<String> bookNames = List.of("Трансерфинг реальности");
                List<Genres> genres = List.of(Genres.COMIC);
                return new GuestOrder(bookNames, genres, this);
            }
        }

        Guest guest = new SecondDummyGuest(librarian);
        assertTrue(manager.putNewBookIntoLibrary(List.of(
            new Book("Трансерфинг реальности", Genres.COMIC))
        ));
        // менеджер удаляет книгу, а другой посетитель хочет ее получить
        assertTrue(manager.removeBookFromLibrary(new Book("Трансерфинг реальности", Genres.COMIC)));
        GuestOrder order = guest.makeAnOrder();
        List<Book> available = guest.askTheLibrarianAboutOrder(order);
        List<Book> chosen = guest.processAvailableBooks(available);
        assertNull(chosen);
        assertThrows(
            NullPointerException.class,
            () -> librarian.giveBooks(chosen, order)
        );
        librarian.sendMessageToGuest(guest);
        assertTrue(guest.isWarned());
    }
}

