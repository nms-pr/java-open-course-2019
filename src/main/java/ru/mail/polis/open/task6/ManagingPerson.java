package ru.mail.polis.open.task6;

import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class ManagingPerson extends Person {
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
            if (!(obj instanceof Manager.Book)) {
                return false;
            }
            Manager.Book other = (Manager.Book) obj;
            return (other.id == id
                    && other.name.equals(name)
                    && Arrays.toString(other.partitions.toArray()).equals(Arrays.toString(partitions.toArray()))
                    && other.currentUser == currentUser);
        }
    }

    protected static HashMap<Book, Integer> store = new HashMap<>();

    protected ManagingPerson(String name) {
        super(name);
    }

    @Override
    protected HashMap<Book, Integer> getCollection() {
        return store;
    }
}
