package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private String name;

    private boolean working;

    private Manager manager;

    private List<Book> booksAll;

    private List<Book> booksAvailable;

    public Library(String name) {
        this.name = name;
        this.booksAll = new ArrayList<>();
        manager = new ManagerImpl(this);
        manager.openInstitution();
    }

    protected List<Book> getBooksAll() {
        return booksAll;
    }

    protected List<Book> getBooksAvailable() {
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
