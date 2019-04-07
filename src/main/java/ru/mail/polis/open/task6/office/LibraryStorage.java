package ru.mail.polis.open.task6.office;

import ru.mail.polis.open.task6.Genres;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;



class LibraryStorage {

    /**
     * - Библиотека
     * - - Есть менеджер
     * - - приносит/убирает новые/старые книги
     * - - открывает/закрывает библиотеку
     * - Есть библиотекарь (добавляет и выдаёт книги)
     * - Есть посетители (берёт и отдаёт книги)
     * - Есть книги
     * - - У книги есть
     * - - - Идентификатор
     * - - - Название
     * - - - Место на полке
     * - - - Раздел к которому она относится
     * - - - Время выдачи и возврата
     * - - - Информация о том кто взял
     * - - Книг с одинаковыми идентификатором несколько
     * - Можно узнать список доступных книг
     * - Можно взять сразу несколько книг
     * - Узнать кто что и когда брал
     * - Напомнить о том что книгу нужно вернуть
     */

    private boolean isOpen;
    private HashMap<Genres, Book> bookStorage;

    LibraryStorage(HashMap<Genres, Book> bookStorage) {
        this.bookStorage = bookStorage;
    }

    boolean isOpen() {
        return isOpen;
    }

    void setOpen() {
        isOpen = true;
    }

    void setClosed() {
        isOpen = false;
    }

    boolean putBook(Book book) {
        if (book != null) {
            bookStorage.put(book.getGenre(), book);
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    boolean putBook(List<Book> bookList) {
        if (bookList != null) {
            for (Book currentBook: bookList) {
                if (putBook(currentBook)) {
                    continue;
                }
                return false;
            }
            return true;
        } else {
            throw new IllegalArgumentException();
        }

    }

    boolean removeBook(Book book) {
        if (book != null) {
            bookStorage.remove(book.getGenre(), book);
            return true;
        } else {
            throw new IllegalArgumentException();
        }

    }

    boolean removeBook(List<Book> bookList) {
        if (bookList != null) {
            for (Book book: bookList) {
                if (removeBook(book)) {
                    continue;
                }
                return false;
            }
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public List<Book> getBooksForGenre(List<Genres> wantedGenres) {
        List<Book> suitableBooks = new ArrayList<>();
        for (Genres genre: wantedGenres) {
            suitableBooks.add(bookStorage.get(genre));
        }
        return suitableBooks;
    }

    public List<Book> getBooksForName(List<String> wantedBookNames) {
        List<Book> suitableBooks = new ArrayList<>();
        Book currentBook;
        Iterator storageIterator = bookStorage.entrySet().iterator();
        while (storageIterator.hasNext()) {
            Map.Entry bookInStorage = (Map.Entry) storageIterator.next();
            currentBook = (Book) bookInStorage.getValue();
            if (wantedBookNames.contains(currentBook.getName())) {
                suitableBooks.add(currentBook);
            }
        }
        return suitableBooks;
    }

    public List<Book> getBooksForGenreAndName(List<String> wantedBookNames, List<Genres> wantedGenres) {
        List<Book> suitableBooks;
        suitableBooks = getBooksForGenre(wantedGenres);
        for (Book currentBook: suitableBooks) {
            if (wantedBookNames.contains(currentBook.getName())) {
                suitableBooks.add(currentBook);
            }
        }
        return suitableBooks;
    }
}
