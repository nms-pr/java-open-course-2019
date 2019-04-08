package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LibraryStorage {

    private static Map<Integer, LinkedList<Book>>  storage = new HashMap<>();
    private static ArrayList<Book> givenBooks = new ArrayList<>();


    static Map<Integer, LinkedList<Book>> getAvailableBooks() {
        return storage;
    }

    static ArrayList<Book> getGivenBooks() {
        return givenBooks;
    }

    static void addNewBook(Book newBook) {
        newBook.setAsIncoming();

        if (storage.containsKey(newBook.getSection())) {
            newBook.setShelfSpace(storage.get(newBook.getSection()).size());
            storage.get(newBook.getSection()).add(newBook);
        } else {
            LinkedList<Book> newShelf = new LinkedList<>();
            newBook.setShelfSpace(newShelf.size());
            newShelf.add(newBook);
            storage.put(newBook.getSection(), newShelf);
        }

    }

    static void removeBook(Book removingBook) {

        if (storage.containsKey(removingBook.getSection())) {
            for (Book book : storage.get(removingBook.getSection())) {
                if (removingBook.equals(book)) {
                    storage.get(book.getSection()).set(book.getShelfSpace(), null);
                    return;
                }
            }
        }
    }

    static void removeBook(int id, int section) {

        if (storage.containsKey(section)) {
            for (Book book: storage.get(section)) {
                if (book.getId() == id) {
                    storage.get(section).set(book.getShelfSpace(), null);
                    return;
                }
            }
        }
    }

    static void receiveBook(Book book, Visitor visitor) {

        for (Book givenBook: givenBooks) {
            if (givenBook.equals(book) && givenBook.getRecipient().equals(visitor)) {
                givenBooks.remove(book);
                break;
            }
        }

        if (storage.get(book.getSection()).get(book.getShelfSpace()) == null) {
            book.setAsIncoming();
            storage.get(book.getSection()).set(book.getShelfSpace(), book);
        }

    }

    static Book giveOutBook(Book givenOutBook, Visitor visitor) {

        if (givenOutBook == null
                || !storage.keySet().contains(givenOutBook.getSection())) {
            return null;
        }

        for (Book book : storage.get(givenOutBook.getSection())) {
            if (book != null && book.getId() == givenOutBook.getId()) {
                book.setAsGivenOut(visitor);
                storage.get(givenOutBook.getSection()).set(book.getShelfSpace(), null);
                givenBooks.add(book);
                return book;
            }
        }

        return null;
    }


}
