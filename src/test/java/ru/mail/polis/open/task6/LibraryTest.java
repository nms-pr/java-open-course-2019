package ru.mail.polis.open.task6;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryTest {

    private static Library library;

    @BeforeAll
    static void fillLibrary() {
        library = new Library(15, 15);
        Manager manager = library.getManager();
        manager.addNewBooks(
                new BookCard("Научная наука", "С.В.Мильке", Section.Scientific, 15)
        );
    }

    @Test
    void testOnePerson() {
        Person person = new Person("Стас", "Мильке", LocalDate.of(2001, 2, 19));
        Librarian librarian = library.getLibrarian();
        String word = librarian.sayHello(person);
        String[] str = librarian.getAvailableBooks();
        PersonCard card = librarian.getPersonCard();
        BookGiven bookGiven = librarian.giveBook(1);
        card.takeBooks(bookGiven);
        librarian.takeBooks(card.returnBook(1));
    }
}
