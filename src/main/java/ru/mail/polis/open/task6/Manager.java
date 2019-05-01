package ru.mail.polis.open.task6;

import java.util.ArrayList;

public class Manager extends ManagingPerson {

    private static ArrayList<String> managerDatabase = new ArrayList<>();
    private static boolean libraryClosed = true;
    private static int managerOperationsDone = 0;
    private static int putsAmount = 0;

    public Manager(String name) {
        super(name, managerDatabase);
    }

    public void addNewBookToStore(Book newBook, int amount) {
        putsAmount++;
        if (store.containsKey(newBook)) {
            managerOperationsDone++;
            bookHashMapOperating(store, newBook, amount);
        } else {
            store.put(newBook, amount);
        }

    }

    protected static int getPutsAmount() {
        return putsAmount;
    }

    protected static int getManagerOperationsDone() {
        return managerOperationsDone;
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

    protected static void checkIfLibraryIsClosed() {
        if (libraryClosed) {
            throw new IllegalStateException("Library is closed!");
        }
    }
}
