package ru.mail.polis.open.task6;

import java.util.ArrayList;

public class Manager extends ManagingPerson {

    private static ArrayList<String> managerDatabase = new ArrayList<>();
    private static boolean libraryClosed = true;

    public Manager(String name) {
        super(name);
    }

    public void addNewBookToStore(Book newBook, int amount) {
        if (store.containsKey(newBook)) {
            bookHashMapOperating(store, newBook, amount);
        } else {
            store.put(newBook, amount);
        }
    }

    public void addNewBookToStore(Book newBook) {
        addNewBookToStore(newBook, 1);
    }

    public void closeLibrary() {
        libraryClosed = true;
    }

    public void openLibrary() {
        libraryClosed = false;
    }

    public static void checkIfLibraryIsClosed() {
        if (libraryClosed) {
            throw new IllegalStateException("Library is closed!");
        }
    }

    @Override
    protected ArrayList<String> getDatabase() {
        return managerDatabase;
    }
}
