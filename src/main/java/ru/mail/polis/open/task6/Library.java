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

    public List<Book.InformationWhoTook> getJournal() {
        List<Book.InformationWhoTook> joutnal = new ArrayList<>();
        for (Book book : getBooksAll()) {
            joutnal.addAll(book.getInformationWhoTooks());
        }
        return joutnal;
    }

    public Map<Book, Visitor> expectedReturnOfBooks() {
        Map<Book, Visitor> booksReturn = Collections.emptyMap();

        List<Book> booksOnHands = new ArrayList<>(booksAll);
        booksOnHands.removeAll(booksAvailable);

        for (Book b : booksOnHands) {
            Visitor lastVisitor = b.getInformationWhoTooks().get(b.getInformationWhoTooks().size() - 1).getVisitor();
            booksReturn.put(b, lastVisitor);
        }
        return booksReturn;
    }
}
