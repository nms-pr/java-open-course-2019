package ru.mail.polis.open.task6;

import java.util.HashMap;

public abstract class ManagingPerson extends Person {

    protected static HashMap<Manager.Book, Integer> store = new HashMap<>();

    public ManagingPerson(String name) {
        super(name);
    }

    @Override
    protected HashMap<Manager.Book, Integer> getCollection() {
        return store;
    }
}
