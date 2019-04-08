package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryStoreTest {

    @BeforeEach
    void refreshStore() {
        LibraryStorage.getGivenBooks().clear();
        LibraryStorage.getAvailableBooks().clear();
    }


    @Test
    void addNewBookTest() {

        Book bookA = new Book("a", 5);
        bookA.setShelfSpace(0);
        LibraryStorage.addNewBook(new Book("a", 5));
        Assertions.assertEquals(1, LibraryStorage.getAvailableBooks().size());
        Assertions.assertTrue(LibraryStorage.getAvailableBooks().get(5).contains(bookA));

        Book bookB = new Book("b",5);
        bookB.setShelfSpace(1);
        LibraryStorage.addNewBook(new Book("b", 5));
        Assertions.assertEquals(2, LibraryStorage.getAvailableBooks().get(5).size());
        Assertions.assertTrue(LibraryStorage.getAvailableBooks().get(5).contains(bookB));

        Book bookC = new Book("c", 7);
        bookC.setShelfSpace(0);
        LibraryStorage.addNewBook(new Book("c", 7));
        Book bookD = new Book("d", 8);
        bookD.setShelfSpace(0);
        LibraryStorage.addNewBook(new Book("d", 8));
        Assertions.assertEquals(3, LibraryStorage.getAvailableBooks().size());
        Assertions.assertTrue(LibraryStorage.getAvailableBooks().get(7).contains(bookC));
        Assertions.assertTrue(LibraryStorage.getAvailableBooks().get(8).contains(bookD));

        Book bookE = new Book("e", 7);
        bookE.setShelfSpace(1);
        LibraryStorage.addNewBook(new Book("e", 7));
        Book bookF = new Book("f", 7);
        bookF.setShelfSpace(2);
        LibraryStorage.addNewBook(new Book("f", 7));
        Assertions.assertEquals(3, LibraryStorage.getAvailableBooks().size());
        Assertions.assertEquals(3, LibraryStorage.getAvailableBooks().get(7).size());
        Assertions.assertTrue(LibraryStorage.getAvailableBooks().get(7).contains(bookE));
        Assertions.assertTrue(LibraryStorage.getAvailableBooks().get(7).contains(bookF));

    }


    @Test
    void removeBookTest() {

        LibraryStorage.addNewBook(new Book("a", 5));
        LibraryStorage.addNewBook(new Book("b", 5));
        LibraryStorage.addNewBook(new Book("c", 7));
        LibraryStorage.addNewBook(new Book("d", 8));
        LibraryStorage.addNewBook(new Book("e", 7));
        LibraryStorage.addNewBook(new Book("f", 7));

        // removing by Book
        Book removingBookF = new Book("f", 7);
        removingBookF.setShelfSpace(2);
        Assertions.assertTrue(LibraryStorage.getAvailableBooks().get(7).contains(removingBookF));
        LibraryStorage.removeBook(removingBookF);
        Assertions.assertFalse(LibraryStorage.getAvailableBooks().get(7).contains(removingBookF));

        Book removingBookA = new Book("a", 5);
        removingBookA.setShelfSpace(0);
        Assertions.assertTrue(LibraryStorage.getAvailableBooks().get(5).contains(removingBookA));
        LibraryStorage.removeBook(removingBookA);
        Assertions.assertFalse(LibraryStorage.getAvailableBooks().get(5).contains(removingBookA));

        // removing by id
        Book removingBookD = new Book("d", 8);
        removingBookD.setShelfSpace(0);
        Assertions.assertTrue(LibraryStorage.getAvailableBooks().get(8).contains(removingBookD));
        LibraryStorage.removeBook("d".hashCode(), 8);
        Assertions.assertFalse(LibraryStorage.getAvailableBooks().get(8).contains(removingBookD));
    }


    @Test
    void giveOutBookTest() {

        LibraryStorage.addNewBook(new Book("Призрачный путь", 17));
        LibraryStorage.addNewBook(new Book("Помощница мертвого оборотня", 31));
        LibraryStorage.addNewBook(new Book("Огромный спор", 13));
        LibraryStorage.addNewBook(new Book("Сказочное одиночество", 17));
        LibraryStorage.addNewBook(new Book("Моё максимальное исследование", 31));
        LibraryStorage.addNewBook(new Book("Школа мятежных воителей", 31));

        Book givenOutBook = new Book("Моё максимальное исследование", 31);
        givenOutBook.setShelfSpace(1);
        Assertions.assertTrue(LibraryStorage.getAvailableBooks().get(31).contains(givenOutBook));
        Assertions.assertEquals(0, LibraryStorage.getGivenBooks().size());
        Visitor testVisitor = new Visitor("Майкл", "Скотт");
        LibraryStorage.giveOutBook(givenOutBook, testVisitor);
        Assertions.assertFalse(LibraryStorage.getAvailableBooks().get(31).contains(givenOutBook));
        Assertions.assertTrue(LibraryStorage.getGivenBooks().contains(givenOutBook));

        givenOutBook = new Book("Огромный спор", 13);
        givenOutBook.setShelfSpace(0);
        Assertions.assertTrue(LibraryStorage.getAvailableBooks().get(13).contains(givenOutBook));
        Assertions.assertEquals(1, LibraryStorage.getGivenBooks().size());
        LibraryStorage.giveOutBook(givenOutBook, testVisitor);
        Assertions.assertFalse(LibraryStorage.getAvailableBooks().get(13).contains(givenOutBook));
        Assertions.assertTrue(LibraryStorage.getGivenBooks().contains(givenOutBook));
        Assertions.assertEquals(2, LibraryStorage.getGivenBooks().size());
    }


    @Test
    void receiveBookTest() {

        LibraryStorage.addNewBook(new Book("Призрачный путь", 17));
        LibraryStorage.addNewBook(new Book("Помощница мертвого оборотня", 31));
        LibraryStorage.addNewBook(new Book("Огромный спор", 13));
        LibraryStorage.addNewBook(new Book("Сказочное одиночество", 17));
        LibraryStorage.addNewBook(new Book("Моё максимальное исследование", 31));
        LibraryStorage.addNewBook(new Book("Школа мятежных воителей", 31));

        Visitor firstTestVisitor = new Visitor("Майкл", "Скотт");
        Book firstGivenOutBook = new Book("Школа мятежных воителей", 31);
        firstGivenOutBook.setShelfSpace(2);
        LibraryStorage.giveOutBook(firstGivenOutBook, firstTestVisitor);
        Book secondGivenOutBook = new Book("Сказочное одиночество", 17);
        secondGivenOutBook.setShelfSpace(1);
        Visitor secondTestVisitor = new Visitor("Двайт","Шрут");
        LibraryStorage.giveOutBook(secondGivenOutBook, secondTestVisitor);

        Assertions.assertFalse(LibraryStorage.getAvailableBooks().get(31).contains(firstGivenOutBook));
        Assertions.assertFalse(LibraryStorage.getAvailableBooks().get(17).contains(secondGivenOutBook));
        Assertions.assertTrue(LibraryStorage.getGivenBooks().contains(secondGivenOutBook));
        Assertions.assertTrue(LibraryStorage.getGivenBooks().contains(secondGivenOutBook));

        LibraryStorage.receiveBook(secondGivenOutBook, secondTestVisitor);
        Assertions.assertTrue(LibraryStorage.getAvailableBooks().get(17).contains(secondGivenOutBook));
        Assertions.assertFalse(LibraryStorage.getGivenBooks().contains(secondGivenOutBook));

        LibraryStorage.receiveBook(firstGivenOutBook, firstTestVisitor);
        Assertions.assertTrue(LibraryStorage.getAvailableBooks().get(31).contains(firstGivenOutBook));
        Assertions.assertFalse(LibraryStorage.getGivenBooks().contains(firstGivenOutBook));

    }

}
