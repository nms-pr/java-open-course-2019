package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.Date;

public class Library {

    static class GiveOutInfo {

        Book book;
        Visitor visitor;
        long date;

        GiveOutInfo(Book book, Visitor visitor) {
            this.book = book;
            this.visitor = visitor;
            this.date = new Date().getTime();
        }
    }

    private static boolean isClosed = false;
    private static ArrayList<GiveOutInfo> giveOutInformationList = new ArrayList<>();


    public Library() {}

    static ArrayList<GiveOutInfo> getGiveOutInformationList() {
        return giveOutInformationList;
    }

    static ArrayList<Book> getTakenBooks() {
        return LibraryStorage.getGivenBooks();
    }

    private static void checkIsClosed() throws Exception {
        if (isClosed) {
            throw new Exception("Sorry, library is closed.");
        }
    }

    // --------------- receiving books ---------------

    static void receiveBook(Book book, Visitor visitor) throws Exception {
        checkIsClosed();
        LibraryStorage.receiveBook(book, visitor);
    }

    // ---------------- giving out books --------------

    static ArrayList<Book> giveOutBooks(Book[] books, Visitor visitor) throws Exception {
        checkIsClosed();

        ArrayList<Book> givingOutBooks = new ArrayList<>();

        for (Book book: books) {
            Book givingOutBook = LibraryStorage.giveOutBook(book, visitor);

            if (givingOutBook != null) {
                givingOutBooks.add(givingOutBook);
                giveOutInformationList.add(new GiveOutInfo(givingOutBook,visitor));
            }
        }

        return givingOutBooks;
    }

    static ArrayList<Book> giveOutBooks(String[] name, int[] section, Visitor visitor) throws Exception {
        if (name.length != section.length) {
            return null;
        }

        Book[] books = new Book[name.length];

        for (int i = 0; i < name.length; i++) {
            books[i] = new Book(name[i],section[i]);
        }

        return giveOutBooks(books,visitor);
    }

    static ArrayList<Book> giveOutBooks(int[] id, int[] section, Visitor visitor) throws Exception {
        if (id.length != section.length) {
            return null;
        }

        Book[] books = new Book[id.length];

        for (int i = 0; i < id.length; i++) {
            books[i] = findBookInAvailable(id[i], section[i]);
        }

        return giveOutBooks(books, visitor);
    }

    private static Book findBookInAvailable(int id, int section) {

        if (LibraryStorage.getAvailableBooks().get(section) == null) {
            return null;
        }

        for (Book availableBook : LibraryStorage.getAvailableBooks().get(section)) {
            if (availableBook != null && availableBook.getId() == id && availableBook.getSection() == section) {
                return availableBook;
            }
        }

        return null;
    }

    // ---------- closing/opening -------------

    static boolean isClosed() {
        return isClosed;
    }

    static void open() {
        isClosed = false;
        Librarian.remindVisitorsToReturnBooks();
    }

    static void close() {
        isClosed = true;
    }
}
