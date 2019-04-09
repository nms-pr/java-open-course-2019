package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;

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

    protected List<Book> getBooksAvailable() {
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
}
