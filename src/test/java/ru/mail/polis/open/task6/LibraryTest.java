package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;


public class LibraryTest {

    private Book firstBook;
    private Book secondBook;
    private Book thirdBook;
    private Book fourthBook;
    private Book fifthBook;
    private Book notExistingBook;
    private Visitor visitor;

    @BeforeEach
    void refreshStore() {
        Library.open();
        LibraryStorage.getAvailableBooks().clear();
        LibraryStorage.getGivenBooks().clear();
        Library.getGiveOutInformationList().clear();

        visitor = new Visitor("Бендер", "Сгибальщик");
    }

    @BeforeEach
    void fillLibrary() {
        firstBook = new Book("Курсы мертвых магов", 10);
        secondBook = new Book("Тайная зима",23);
        thirdBook = new Book("Одноглазая ловушка",23);
        fourthBook = new Book("Страницы каждой жизни",10);
        fifthBook = new Book("Море убитой веры",17);
        notExistingBook = new Book("Талантливый спор", 15);
        LibraryStorage.addNewBook(firstBook);
        LibraryStorage.addNewBook(secondBook);
        LibraryStorage.addNewBook(thirdBook);
        LibraryStorage.addNewBook(fourthBook);
        LibraryStorage.addNewBook(fifthBook);
        firstBook.setShelfSpace(0);
        secondBook.setShelfSpace(0);
        thirdBook.setShelfSpace(1);
        fourthBook.setShelfSpace(1);
        fifthBook.setShelfSpace(0);
        notExistingBook.setShelfSpace(0);
    }


    @Test
    void giveOutBookByBookTest() {

        Book[] wantedBooks = {secondBook, fifthBook, firstBook};
        ArrayList<Book> expectingReceivedBooks = new ArrayList<>();
        expectingReceivedBooks.add(secondBook);
        expectingReceivedBooks.add(fifthBook);
        expectingReceivedBooks.add(firstBook);


        try {
            ArrayList<Book> receivedBooks = Library.giveOutBooks(wantedBooks, visitor);
            Assertions.assertEquals(expectingReceivedBooks, receivedBooks);
        } catch (Exception e) {
            fail(e);
        }

    }

    @Test
    void giveOutBookByBookWithNotExistingBookTest() {

        ArrayList<Book> expectingReceivedBooks = new ArrayList<>();
        Book[] wantedBooksWithNotExisting = {thirdBook, notExistingBook, fourthBook};
        expectingReceivedBooks.add(thirdBook);
        expectingReceivedBooks.add(fourthBook);

        try {
            ArrayList<Book> receivedBooks = Library.giveOutBooks(wantedBooksWithNotExisting, visitor);
            Assertions.assertEquals(expectingReceivedBooks, receivedBooks);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void giveOutBooksByName() {

        String[] wantedStrings = {"Море убитой веры", "Курсы мертвых магов", "Одноглазая ловушка"};
        int[] wantedSections = {17, 10, 23};
        ArrayList<Book> expectingReceivedBooks = new ArrayList<>();
        expectingReceivedBooks.add(fifthBook);
        expectingReceivedBooks.add(firstBook);
        expectingReceivedBooks.add(thirdBook);

        try {
            ArrayList<Book> receivedBooks = Library.giveOutBooks(wantedStrings, wantedSections, visitor);
            Assertions.assertEquals(expectingReceivedBooks, receivedBooks);
        } catch (Exception e) {
            fail(e);
        }

    }

    @Test
    void giveOutBookWithNotCoincidingLengthTest() {
        // name.length != section.length
        String[] wantedString = {"Море убитой веры", "Страницы каждой жизни", "Тайная зима"};
        int[] wantedSection = {10, 23};

        try {
            ArrayList<Book> receivedBooks = Library.giveOutBooks(wantedString, wantedSection, visitor);
            Assertions.assertNull(receivedBooks);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void giveOutBookByNameWithNotExistingBookTest() {

        ArrayList<Book> expectingReceivedBooks = new ArrayList<>();

        String[] wantedStringWithNotExisting = {"Талантливый спор", "Страницы каждой жизни", "Тайная зима"};
        int[] wantedSectionsWithNotExisting = {15, 10, 23};
        expectingReceivedBooks.add(fourthBook);
        expectingReceivedBooks.add(secondBook);

        try {
            ArrayList<Book> receivedBooks = Library.giveOutBooks(
                    wantedStringWithNotExisting,
                    wantedSectionsWithNotExisting,
                    visitor);
            Assertions.assertEquals(expectingReceivedBooks, receivedBooks);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void giveOutBooksByIdTest() {

        int[] wantedIds = {"Курсы мертвых магов".hashCode(), "Тайная зима".hashCode(), "Одноглазая ловушка".hashCode()};
        int[] wantedSections = {10, 23, 23};
        ArrayList<Book> expectingReceivedBooks = new ArrayList<>();
        expectingReceivedBooks.add(firstBook);
        expectingReceivedBooks.add(secondBook);
        expectingReceivedBooks.add(thirdBook);

        try {
            ArrayList<Book> receivedBooks = Library.giveOutBooks(wantedIds, wantedSections, visitor);
            Assertions.assertEquals(expectingReceivedBooks, receivedBooks);
        } catch (Exception e) {
            fail(e);
        }

    }

    @Test
    void giveOutBooksByIdWithNotCoincidingLengthsTest() {
        // name.length != section.length
        int[] wantedId = {"Море убитой веры".hashCode(), "Страницы каждой жизни".hashCode(), "Тайная зима".hashCode()};
        int[] wantedSection = {10, 23};

        try {
            ArrayList<Book> receivedBooks = Library.giveOutBooks(wantedId, wantedSection, visitor);
            Assertions.assertNull(receivedBooks);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void getGiveOutInformationListTest() {
        Visitor firstVisitor = new Visitor("Дима", "Билан");

        Assertions.assertEquals(0, Library.getGiveOutInformationList().size());
        firstVisitor.getBooks(new String[]{"Одноглазая ловушка"}, new int[] {23});
        Assertions.assertEquals(1, Library.getGiveOutInformationList().size());
        Assertions.assertEquals(firstVisitor, Library.getGiveOutInformationList().get(0).visitor);
        Assertions.assertEquals(thirdBook, Library.getGiveOutInformationList().get(0).book);

        Visitor secondVisitor = new Visitor("Стас", "Давыдов");
        secondVisitor.getBooks(new String[] {"Море убитой веры", "Тайная зима"}, new int[] {17, 23});
        Assertions.assertEquals(3, Library.getGiveOutInformationList().size());
        Assertions.assertEquals(secondVisitor, Library.getGiveOutInformationList().get(1).visitor);
        Assertions.assertEquals(secondVisitor, Library.getGiveOutInformationList().get(2).visitor);

    }

}
