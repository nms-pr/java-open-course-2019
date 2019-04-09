package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Library {

    private String name;
    private boolean working;

    private Manager manager;
    private Librarian librarian;

    private List<Book> booksAll;
    private List<Book> booksAvailable;

    public Library(String name) {
        this.name = name;

        manager = new ManagerImpl(this);
        manager.openInstitution();

        librarian = new LibrarianImpl(this);
    }

    protected List<Book> getBooksAll() {
        if (booksAll == null) {
            booksAll = new ArrayList<>();
        }
        return booksAll;
    }

    public List<Book> getBooksAvailable() {
        if (booksAvailable == null) {
            booksAvailable = new ArrayList<>(booksAll);
        }
        return booksAvailable;
    }

    protected void open() {
        working = true;
    }


    protected void close() {
        working = false;
    }

    public boolean isWorking() {
        return working;
    }

    public String getName() {
        return name;
    }

    public boolean addBook(Book book) {
        if (working) {
            return manager.bringBook(book);
        }
        return false;
    }

    public boolean addBooks(Book... books) {
        if (isWorking()) {
            for (int i = 0; i < books.length; i++) {
                addBook(books[i]);
            }
            return true;
        }
        return false;
    }

    public boolean removeBook(Book book) {
        if (working) {
            return manager.removeBook(book);
        }
        return false;
    }

    public Book getBook(Book book) {
        if (working) {
            return librarian.issueBook(book);
        }
        return null;
    }

    public List<Book> getBooks(Book... books) {
        if (working) {
            List<Book> bookList = new ArrayList<>();
            for (int i = 0; i < books.length; i++) {
                bookList.add(librarian.issueBook(books[i]));
            }
            return bookList;
        }
        return null;
    }

    public List<Book> searhBook(String data) {
        if (working) {
            List<Book> books = new ArrayList<>();
            for (Book b : booksAvailable) {
                if (b.toString().toLowerCase().contains(data.toLowerCase())) {
                    books.add(b);
                }
            }
        }
        return null;
    }

    public boolean returnBook(Book book) {
        if (working && booksAll.contains(book)) {
            librarian.addBook(book);
        }
        return false;
    }

    public List<Book.InformationWhoTook> getJournal() {
        List<Book.InformationWhoTook> joutnal = new ArrayList<>();
        for (Book book : booksAll) {
            joutnal.addAll(book.getInformationWhoTooks());
        }
        return joutnal;
    }

    public Map<Book, Visitor> expectedReturnOfBooks() {
        Map<Book, Visitor> booksReturn = Collections.emptyMap();

        List<Book> booksOnHands = new ArrayList<>(booksAll);
        booksOnHands.removeAll(booksAvailable);

        for (Book b : booksOnHands) {
            List<Book.InformationWhoTook> iwt = b.getInformationWhoTooks();
            int lastIndex = iwt.size() - 1;
            Visitor lastVisitor = iwt.get(lastIndex).getVisitor();
            booksReturn.put(b, lastVisitor);
        }
        return booksReturn;
    }
}
