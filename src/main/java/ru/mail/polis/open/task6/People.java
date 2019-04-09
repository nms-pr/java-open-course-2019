package ru.mail.polis.open.task6;

import ru.mail.polis.open.task6.library.Book;

import java.util.List;

public abstract class People {
    private String name;

    public People(String name) {
        this.name = name;
    }

    public abstract void put(Book book);

    public abstract List get(Book book);

}
