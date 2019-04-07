package ru.mail.polis.open.task6.LibraryOffice;

import java.util.List;

class Manager {

    private LibraryStorage bookStorage;

    Manager(LibraryStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    boolean closeLibrary() {
        if (bookStorage.isOpen()) {
            bookStorage.setClosed();
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


}
