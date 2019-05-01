package ru.mail.polis.open.task6;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public abstract class ManagingPerson extends Person {
    private static int putsAmount = 0;
    private static int removesAmount = 0;

    protected static class Store {
        private HashMap<Book, Integer> store = new HashMap<>();

        protected void put(Book book, Integer amount) {
            if (amount > 0) {
                putsAmount++;
            }
            if (putsAmount != Manager.getPutsAmount() + LibraryClient.getPutsAmount()) {
                throw new IllegalCallerException("Cheating!");
            }
            store.put(book, amount);
        }

        protected void remove(Book book) {
            removesAmount++;
            if (removesAmount != LibraryClient.getRemovesAmount()) {
                throw new IllegalCallerException("Cheating!");
            }
            store.remove(book);
        }

        protected boolean containsKey(Book key) {
            for (Book book: store.keySet()) {
                if (book.equals(key)) {
                    return true;
                }
            }
            return false;
        }

        protected int get(Book key) {
            return store.get(key);
        }

        protected Set<Book> keySet() {
            return store.keySet();
        }
    }

    protected static Store store = new Store();

    protected ManagingPerson(String name, Serializable database) {
        super(name, database);
    }
}
