package ru.mail.polis.open.task6;

import java.util.List;

public interface Visitor {
    void takeBook(String name, String author);
    void takeBook(String[] names, String[] authors);
    void takeBook(long ID);
    void takeBook(long[] IDs);
    void giveAway(Book book);
    void giveAway(List<Book> books);
}