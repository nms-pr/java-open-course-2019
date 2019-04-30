package ru.mail.polis.open.task6;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Person {

    private static final String UNSUPPORTED_COLLECTION_TYPE_MESSAGE =
            "This Person's Book Collection is something strange";
    private static final String NON_UNIQUE_NAME_MESSAGE = "Name must be unique!";
    protected static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private final String name;

    protected Person(String name) {
        if (getDatabase() instanceof HashMap) {
            HashMap database = (HashMap) getDatabase();
            if (database.containsKey(name)) {
                throw new IllegalArgumentException(NON_UNIQUE_NAME_MESSAGE);
            }
            database.put(name, this);
        } else {
            if (getDatabase() instanceof ArrayList) {
                ArrayList database = (ArrayList) getDatabase();
                if (database.contains(name)) {
                    throw new IllegalArgumentException(NON_UNIQUE_NAME_MESSAGE);
                }
                database.add(name);
            } else {
                throw new IllegalArgumentException(UNSUPPORTED_COLLECTION_TYPE_MESSAGE);
            }
        }
        this.name = name;
    }

    public enum BookSearchParameter {
        BY_NAME, BY_PARTITIONS, BY_PARTITION
    }

    public String getName() {
        return name;
    }

    protected void bookHashMapOperating(HashMap collection, ManagingPerson.Book book, int amountChange) {
        int previousAmount = (int) collection.get(book);
        collection.put(book, previousAmount + amountChange);
    }

    protected ManagingPerson.Book removeOneBookFromCollection(ManagingPerson.Book bookToRemove) {
        if (getCollection() instanceof HashMap) {
            HashMap collection = (HashMap) getCollection();
            if (collection.containsKey(bookToRemove)) {
                if ((int) collection.get(bookToRemove) == 1) {
                    collection.remove(bookToRemove);
                } else {
                    bookHashMapOperating(collection, bookToRemove, -1);
                }
            }
        } else {
            if (getCollection() instanceof ArrayList) {
                ArrayList collection = (ArrayList) getCollection();
                collection.remove(bookToRemove);
            } else {
                throw new IllegalArgumentException(UNSUPPORTED_COLLECTION_TYPE_MESSAGE);
            }
        }
        return bookToRemove;
    }

    protected void addOneBookToCollection(ManagingPerson.Book bookToAdd) {
        if (getCollection() instanceof HashMap) {
            HashMap collection = (HashMap) getCollection();
            if (collection.containsKey(bookToAdd)) {
                bookHashMapOperating(collection, bookToAdd, 1);
            } else {
                collection.put(bookToAdd, 1);
            }
        } else {
            if (getCollection() instanceof ArrayList) {
                ((ArrayList) getCollection()).add(bookToAdd);
            } else {
                throw new IllegalArgumentException(UNSUPPORTED_COLLECTION_TYPE_MESSAGE);
            }
        }
    }

    protected abstract Serializable getDatabase();

    protected abstract Serializable getCollection();

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
