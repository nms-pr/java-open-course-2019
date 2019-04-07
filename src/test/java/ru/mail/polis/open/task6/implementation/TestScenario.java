package ru.mail.polis.open.task6.implementation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task6.implementation.Book.Book;
import ru.mail.polis.open.task6.implementation.Book.Category;
import ru.mail.polis.open.task6.implementation.people.Customer;
import ru.mail.polis.open.task6.implementation.people.Librarian;
import ru.mail.polis.open.task6.implementation.people.Manager;
import ru.mail.polis.open.task6.implementation.people.Person;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestScenario {

    private static Manager manager;
    private static Librarian librarian;
    private static Library library;
    private static BookShelf bookShelf;

    private static OutputStream defaultOutputStream;
    private static ByteArrayOutputStream testOutputStream;

    @BeforeAll
    static void initializeUtils() {

        testOutputStream = new ByteArrayOutputStream();
        defaultOutputStream = System.out;
        System.setOut(new PrintStream(testOutputStream));
    }

    @BeforeAll
    static void init() {

        manager = new Manager(new Person("Bogvzyan", "Maschenko"));
        librarian = new Librarian(new Person("NotBogvzyan", "NotMaschenko"));
        bookShelf = new BookShelf();
        library = new Library(bookShelf, manager, librarian);
        librarian.assignToLibrary(library);
        manager.assignToLibrary(library);
    }

    @Test
    void playScenario() {

        // Наполняем библиотеку
        addingFirstBooks();
        checkForFirstBooks(); // проверяем, что все на местах


        // Первый рабочий день
        manager.openLibrary();

        // И первый посетитель
        Customer customer1 = new Customer(new Person("Vova", "Vovovich"), library);

        // О, книги, никогда не видел! Дайте любую!!!!!
        customer1.takeAnyBook();
        customer1.readBooks();
        // Короткая книга, может позже зайду
        customer1.retrieveAllBooks();
        checkForFirstBooks();

        checkWhetherSomethingIsWrittenToOutputStream();

        // О, хочу о паттернах и о C#!
        Set<Book> books = customer1.getBooksByAuthor("Ayn Rand");
        Book wantedBook = new Book(
            "Design patterns via c#",
            "Alexander Shevchuk",
            Category.PROGRAMMING
        );
        if (books.contains(wantedBook)) {

            customer1.takeBook(wantedBook);
        }

        // Пока первый смотрит UMLки, у нас новый посетитель
        Customer customer2 = new Customer(new Person("NotVova", "NotVovovich"), library); // надо все-таки начинать гуглить имена и фамилии

        customer2.takeAnyBook();

        // Ой, чето подустал, дайте что-нибудь еще
        customer1.takeAnyBook();

        // Никто не забыл сдать книги?
        librarian.notifyAllCustomersWithBooks();

        checkWhetherSomethingIsWrittenToOutputStream();

        //Надо быстро дочитать, пока по шапке не надавали
        customer1.readBooks();

        checkWhetherSomethingIsWrittenToOutputStream();

        customer1.retrieveAllBooks();

        // Второй даже не начиал читать
        customer2.retrieveAllBooks();


        // А что, менеджеры тоже люди
        Customer readingManager = new Customer(manager, library);

        wantedBook = new Book(
            "Atlas Shrugged",
            "Ayn Rand",
            Category.FICTION
        );
        if (books.contains(wantedBook)) {

            readingManager.takeBook(wantedBook);
        }

        librarian.notifyAllCustomersWithBooks();
        checkWhetherSomethingIsWrittenToOutputStream();

        // Просрочил книгу, но не хочешь связываться с библиотекарем?
        // Превысь свои должностные полномочия и сам стань библиотекарем!
        Librarian trickyManager = new Librarian(manager);
        trickyManager.assignToLibrary(library);
        library.setLibrarian(trickyManager);
        readingManager.retrieveAllBooks();

        checkForFirstBooks();

        // Только не забудь оставить все, как и было
        library.setLibrarian(librarian);
        assertEquals(librarian, library.getLibrarian());

        // 20:00 День кончился, всем спасибо, все свободны
        manager.closeLibrary();

        // 20:01
        // - Мне срочно нужна книга!!!
        // - Простите, но мы закрыты
        assertThrows(IllegalStateException.class, () -> customer1.getBooksByCategory(Category.FICTION));

        // Но если очень хочется, то вам мы дадим
        manager.openLibrary();


        customer1.takeBooksByCategory(Category.HISTORY);

        manager.closeLibrary();

        // Книгу забрали домой
        assertEquals(0, bookShelf.getBookInfo(new Book(
            "Napoleon Bonaparte",
            "Manfred",
            Category.HISTORY
        )).getInStock());


        // Следующтй день
        manager.openLibrary();

        librarian.notifyAllCustomersWithBooks();
        checkWhetherSomethingIsWrittenToOutputStream();

        customer1.retrieveAllBooks();
        checkForFirstBooks();

        manager.deleteBook("Atlas Shrugged",
            "Ayn Rand",
            Category.FICTION
        );

        assertEquals(
            2,
            bookShelf.getBookInfo(new Book("Atlas Shrugged",
                "Ayn Rand",
                Category.FICTION)).getTotal());

        // Жизнь продолжается ...
    }

    private void checkWhetherSomethingIsWrittenToOutputStream() {
        assertTrue(testOutputStream.size() > 0);
        testOutputStream.reset();
    }

    private void checkForFirstBooks() {

        checkBooksAreSame();

        checkTotalQuantityIsCorrect();

        checkQuantityInStockIsCorrect();
    }

    private void checkQuantityInStockIsCorrect() {
        assertEquals(2, bookShelf.getBookInfo(new Book(
            "Design patterns via c#",
            "Alexander Shevchuk",
            Category.PROGRAMMING
        )).getInStock());

        assertEquals(1, bookShelf.getBookInfo(new Book(
            "Design Patterns: Elements of Reusable Object-Oriented Software",
            "GOF",
            Category.PROGRAMMING
        )).getInStock());

        assertEquals(3, bookShelf.getBookInfo(new Book(
            "Atlas Shrugged",
            "Ayn Rand",
            Category.FICTION
        )).getInStock());

        assertEquals(1, bookShelf.getBookInfo(new Book(
            "Napoleon Bonaparte",
            "Manfred",
            Category.HISTORY
        )).getInStock());
    }

    private void checkTotalQuantityIsCorrect() {
        assertEquals(2, bookShelf.getBookInfo(new Book(
            "Design patterns via c#",
            "Alexander Shevchuk",
            Category.PROGRAMMING
        )).getTotal());

        assertEquals(1, bookShelf.getBookInfo(new Book(
            "Design Patterns: Elements of Reusable Object-Oriented Software",
            "GOF",
            Category.PROGRAMMING
        )).getTotal());

        assertEquals(3, bookShelf.getBookInfo(new Book(
            "Atlas Shrugged",
            "Ayn Rand",
            Category.FICTION
        )).getTotal());

        assertEquals(1, bookShelf.getBookInfo(new Book(
            "Napoleon Bonaparte",
            "Manfred",
            Category.HISTORY
        )).getTotal());
    }

    private void checkBooksAreSame() {
        assertEquals(
            Set.of(
                new Book(
                    "Design patterns via c#",
                    "Alexander Shevchuk",
                    Category.PROGRAMMING
                ),
                new Book(
                    "Design Patterns: Elements of Reusable Object-Oriented Software",
                    "GOF",
                    Category.PROGRAMMING
                ),
                new Book(
                    "Atlas Shrugged",
                    "Ayn Rand",
                    Category.FICTION
                ),
                new Book(
                    "Napoleon Bonaparte",
                    "Manfred",
                    Category.HISTORY
                )
            ),

            bookShelf.getAllBooks()
        );
    }

    private void addingFirstBooks() {
        manager.addBook(
            new Book(
                "Design patterns via c#",
                "Alexander Shevchuk",
                Category.PROGRAMMING
            )
        );
        manager.addBook(
            new Book(
                "Design patterns via c#",
                "Alexander Shevchuk",
                Category.PROGRAMMING
            )
        );
        manager.addBook(
            new Book(
                "Design Patterns: Elements of Reusable Object-Oriented Software",
                "GOF",
                Category.PROGRAMMING
            )
        );
        manager.addBook(
            new Book(
                "Atlas Shrugged",
                "Ayn Rand",
                Category.FICTION
            )
        );
        manager.addBook(
            new Book(
                "Atlas Shrugged",
                "Ayn Rand",
                Category.FICTION
            )
        );
        manager.addBook(
            new Book(
                "Atlas Shrugged",
                "Ayn Rand",
                Category.FICTION
            )
        );
        manager.addBook(
            new Book(
                "Napoleon Bonaparte",
                "Manfred",
                Category.HISTORY
            )
        );
    }

    @AfterAll
    static void revertAllChanges() {
        System.setOut(new PrintStream(defaultOutputStream));
    }
}
