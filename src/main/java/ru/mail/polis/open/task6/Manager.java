package ru.mail.polis.open.task6;

import java.util.ArrayList;

public class Manager extends ManagingPerson {

    private static ArrayList<String> managerDatabase = new ArrayList<>();
    private static boolean libraryClosed = true;
    private static int operationsCount = 0;
    private static int putsCount = 0;

    public Manager(String name) {
        super(name, managerDatabase);
    }

    public void addNewBookToStore(Book newBook, int amount) {
        putsCount++;
        if (store.containsKey(newBook)) {
            operationsCount++;
            bookHashMapOperating(store, newBook, amount);
        } else {
            store.put(newBook, amount);
        }

    }

    public void addNewBookToStore(Book newBook) {
        addNewBookToStore(newBook, 1);
    }

    protected static int getPutsCount() {
        return putsCount;
    }

    protected static int getOperationsCount() {
        return operationsCount;
    }

    public void closeLibrary() {
        libraryClosed = true;
    }

    public void openLibrary() {
        libraryClosed = false;
    }

    protected static void checkIfLibraryIsClosed() {
        if (libraryClosed) {
            throw new IllegalStateException("Library is closed!");
        }
    }
}
