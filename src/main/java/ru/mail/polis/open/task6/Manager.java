package ru.mail.polis.open.task6;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Manager extends ManagingPerson {
    public static class Book {

        private static final int USAGE_TIME = 15;

        protected final int id;
        protected final String name;
        protected ArrayList<String> partitions;
        protected LibraryClient currentUser;
        private Calendar takeTime;
        private Calendar returnTime;

        public Book(int id, String name, ArrayList<String> partitions) {
            this.id = id;
            this.name = name;
            this.partitions = new ArrayList<>(partitions);
            currentUser = null;
            returnTime = new GregorianCalendar();
            takeTime = new GregorianCalendar();
        }

        public Book(int id, String name) {
            this(id, name, null);
        }

        protected void setTakeAndReturnTime() {
            takeTime = Calendar.getInstance();
            returnTime = Calendar.getInstance();
            extend();
        }

        protected void extend() {
            returnTime.add(Calendar.DAY_OF_MONTH, USAGE_TIME);
        }

        protected void setCurrentUser(@Nullable LibraryClient client) {
            currentUser = client;
        }

        protected void markAsUnused() {
            setCurrentUser(null);
        }

        protected LibraryClient getCurrentUser() {
            return currentUser;
        }

        protected Calendar getReturnTime() {
            return returnTime;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            String lineSeparator = System.getProperty("line.separator");
            res.append("id: ")
                    .append(id)
                    .append("; ")
                    .append("name: ")
                    .append(name)
                    .append(lineSeparator)
                    .append("partitions: ");
            for (String s: partitions) {
                res.append(s).append(", ");
            }
            return res.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Book)) {
                return false;
            }
            Book other = (Book) obj;
            return (other.id == id
                    && other.name.equals(name)
                    && Arrays.toString(other.partitions.toArray()).equals(Arrays.toString(partitions.toArray()))
                    && other.currentUser == currentUser);
        }
    }

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
