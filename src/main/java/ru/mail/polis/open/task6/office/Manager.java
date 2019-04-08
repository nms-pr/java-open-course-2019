package ru.mail.polis.open.task6.office;

import java.util.List;

class Manager {

    private LibraryStorage bookStorage;
    private Librarian librarian;

    Manager(LibraryStorage bookStorage, Librarian librarian) {
        this.bookStorage = bookStorage;
        this.librarian = librarian;
    }

    boolean closeLibrary() {
        if (bookStorage.isOpen()) {
            bookStorage.setClosed();
            return true;
        }
        return false;
    }

    boolean openLibrary() {
        if (!bookStorage.isOpen()) {
            bookStorage.setOpen();
            librarian.checkDebts();
            return true;
        }
        return false;
    }

    boolean putNewBookIntoLibrary(Book book) {
        return bookStorage.putBook(book);
    }

    boolean putNewBookIntoLibrary(List<Book> bookList) {
        return bookStorage.putBook(bookList);
    }

    boolean removeBookFromLibrary(Book book) {
        return bookStorage.removeBook(book);
    }

    boolean removeBookFromLibrary(List<Book> bookList) {
        return bookStorage.removeBook(bookList);
    }

}
