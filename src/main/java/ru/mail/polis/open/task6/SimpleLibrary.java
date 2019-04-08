package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.mail.polis.open.task6.BookInfo.TakerInfo;

public class SimpleLibrary implements ManagableLibrary, BorrowableLibrary {
    private Book[] shelf;
    private Map<Book, BookInfo> booksInfo;
    private boolean isOpen;

    SimpleLibrary(int shelfCapacity) {
        isOpen = true;
        shelf = new Book[shelfCapacity];
        booksInfo = new HashMap<Book, BookInfo>(shelfCapacity);
    }

    @Override
    public void takeBook(Book book, Visitor visitor) throws LibraryException {
        if (!isOpen) {
            throw new LibraryException("Library is closed");
        }
        BookInfo bookInfo = booksInfo.get(book);
        shelf[bookInfo.getShelfPlaceId()] = null;
        bookInfo.markBookAsTaken(visitor);
    }

    @Override
    public void returnBook(Book book) throws LibraryException {
        if (!isOpen) {
            throw new LibraryException("Library is closed");
        }
        int shelfPlace = addToShelf(book);
        BookInfo bookInfo = booksInfo.get(book);
        bookInfo.markBookAsReturned(shelfPlace);
    }

    @Override
    public void addToLibrary(Collection<Book> books) throws LibraryException {
        if (books == null) {
            return;
        }
        for (Book book : books) {
            addToLibrary(book);
        }
    }

    @Override
    public void addToLibrary(Book book) throws LibraryException {
        if (!isOpen) {
            throw new LibraryException("Library is closed");
        }
        if (booksInfo.get(book) == null) {
            int shelfPlaceId = addToShelf(book);
            booksInfo.put(book, new BookInfo(shelfPlaceId));
        }
    }

    private int addToShelf(Book book) throws LibraryException {
        for (int i = 0; i < shelf.length; i++) {
            if (shelf[i] == null) {
                shelf[i] = book;
                return i;
            }
        }
        throw new LibraryException("Shelf is full");
    }

    @Override
    public void remove(Book book) throws LibraryException {
        if (!isOpen) {
            throw new LibraryException("Library is closed");
        }
        BookInfo bookInfo = booksInfo.get(book);
        shelf[bookInfo.getShelfPlaceId()] = null;
        booksInfo.remove(book);
    }

    @Override
    public void open() {
        isOpen = true;
    }

    @Override
    public void close() {
        isOpen = false;
    }

    @Override
    public Book searchForBook(String title) {
        for (Book book : shelf) {
            if (book != null && book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> getAvailableBooks() {
        List<Book> list = new ArrayList<Book>(Arrays.asList(shelf));
        list.removeIf(b -> b == null);
        return list;
    }

    @Override
    public List<TakerInfo> getTakersHistory(Book book) {
        return booksInfo.get(book).getTakersHistory();
    }

    @Override
    public List<Visitor> getCurrentTakers() {
        List<Visitor> visitors = new ArrayList<Visitor>();
        for (BookInfo bookInfo : booksInfo.values()) {
            if (bookInfo.isBookTaken()) {
                visitors.add(bookInfo.getCurrentTaker());
            }
        }
        return visitors;
    }
}
