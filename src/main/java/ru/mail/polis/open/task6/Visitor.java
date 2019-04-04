package ru.mail.polis.open.task6;

import java.util.List;

public interface Visitor {

    void takeBook(String name, String author);

    void takeBook(String[] names, String[] authors);

    void takeBook(long id);

    void takeBook(long[] ids);

    void giveAway(Book book);

    void giveAway(List<Book> books);
}